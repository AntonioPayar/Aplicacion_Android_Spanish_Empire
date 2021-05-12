package com.example.proyecto.Base;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.proyecto.Clases.ProductoNombre;
import com.example.proyecto.Clases.Reinos;
import com.example.proyecto.Clases.Mercancia;
import com.example.proyecto.Clases.Productos;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BaseDatos extends SQLiteOpenHelper {


    public BaseDatos(@Nullable Context context) {
        super(context, "Espana_sqlite.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db1) {
        //this.deleteTables();
        //db.execSQL("CREATE TABLE hola(name TEXT primary key,adios TEXT)");
        System.out.println("Base de datos conectada correctamente");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**Metodo encargado de insertar las partidas**/
    public void insertPartidas(String user){
        SQLiteDatabase db;
        String hora = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            ContentValues valores = new ContentValues();
            valores.put("usuario",user);
            valores.put("hora_inicio",hora);
            db=this.getWritableDatabase();
            db.insert("Partidas",null,valores);

    }

    public void updateFinPartidas(String user,int contador){
        SQLiteDatabase db;
        String hora = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        db=this.getWritableDatabase();
        db.execSQL("UPDATE Partidas SET hora_fin='"+hora+"',turnos_jugados="+contador+" WHERE usuario='"+user+"'");

    }

    /**Metodo encargado de recorrer la tabla Paises para comprobar que el ususario no ha introducido un nombre de partida igual**/
    public boolean recorrerNombrePartida(String nombre){
        SQLiteDatabase db;
        Cursor cursor;
        int cantidad=1;
        db=this.getReadableDatabase();

        cursor=db.rawQuery("SELECT count(usuario) FROM Partidas WHERE usuario='"+nombre+"'",null);

        while(cursor.moveToNext()){
            cantidad=cursor.getInt(0);
        }

        if(cantidad==0){
            return true;
        }else{
            return false;
        }
    }

    /**Metodo encargado de insertar las turnos**/
    public void insertTurnos(int turno){
        SQLiteDatabase db;
        db=this.getWritableDatabase();
        db.execSQL("INSERT INTO Turnos(turno,id_partida)VALUES("+turno+",(SELECT max(id_partida) FROM Partidas))");
    }

    /**Metodo encargado de insertar las demandas**/
    public void insertDemandas(ProductoNombre product, String zona){
        SQLiteDatabase db;
        db=this.getWritableDatabase();
        db.execSQL("INSERT INTO Demandas(id_turno,descripcion,id_pais,id_partida)VALUES((SELECT max(turno) FROM Turnos WHERE id_partida=(SELECT MAX(id_partida)FROM Partidas)),'"+product.toString()+"',(SELECT id_pais FROM Paises WHERE nombre='"+zona+"'),(SELECT max(id_partida) FROM Partidas))");
    }

    /**Metodo encargado de insertar las Paises**/
    public void insertPaises(String paisnombre,String continente,int poblacion){
        SQLiteDatabase db;
        Cursor cursor;
        int cantidad=1;
        db=this.getReadableDatabase();

        cursor=db.rawQuery("SELECT count(id_pais) FROM Paises WHERE nombre='"+paisnombre+"'",null);

        while(cursor.moveToNext()){
            cantidad=cursor.getInt(0);
        }

        if(cantidad==0){
            ContentValues valores = new ContentValues();
            valores.put("nombre",paisnombre);
            valores.put("continente",continente);
            valores.put("poblacion",poblacion);
            db=this.getWritableDatabase();
            db.insert("Paises",null,valores);
        }
        //db.execSQL("INSERT INTO Paises(nombre,continente,poblacion)VALUES("+paisnombre+","+continente+","+poblacion+")");
    }

    /**Metodo encargado de insertar las Flotas**/
    public void insertFLotas(String zona){
        SQLiteDatabase db;
        db=this.getWritableDatabase();

        db.execSQL("INSERT INTO Flota(id_pais,id_partida)VALUES((SELECT id_pais FROM Paises WHERE nombre='"+zona+"'),(SELECT max(id_partida) FROM Partidas))");

        //db.execSQL("INSERT INTO Paises(nombre,continente,poblacion)VALUES("+paisnombre+","+continente+","+poblacion+")");
    }

    /**Metodo encargado de insertar las Producciones**/
    public void insertProducciones(Productos producto, String zona){
        SQLiteDatabase db;
        db=this.getWritableDatabase();

        db.execSQL("INSERT INTO Producciones (id_pais,id_turno,producto, cantidad, id_partida) VALUES ((SELECT id_pais FROM Paises WHERE nombre='"+zona+"'),(SELECT max(turno) FROM Turnos WHERE id_partida=(SELECT MAX(id_partida)FROM Partidas)),'"+producto.getNombre()+"',"+producto.getCantidad()+",(SELECT max(id_partida) FROM Partidas))");
    }

    /**Metodo encargado de insertar las Mercancias**/
    public void insertMercancias(Reinos pais, int cantidad, ProductoNombre producto){
        SQLiteDatabase db;
        db=this.getWritableDatabase();

        db.execSQL("INSERT INTO Mercancias(id_turno,id_pais,id_produccion,cantidad,id_partida) VALUES((SELECT max(turno) FROM Turnos WHERE id_partida=(SELECT MAX(id_partida)FROM Partidas)),(SELECT id_pais FROM Paises WHERE nombre='"+pais.getNombre()+"'),(SELECT id_produccion FROM Producciones WHERE id_pais=(SELECT id_pais FROM Paises WHERE nombre='"+pais.getNombre()+"') and id_turno=(SELECT max(turno) FROM Turnos WHERE id_partida=(SELECT MAX(id_partida)FROM Partidas)) and producto ='"+producto+"' and id_partida=(SELECT max(id_partida) FROM Partidas)),"+cantidad+",(SELECT max(id_partida) FROM Partidas))");
        //INSERT INTO Mercancias(id_turno,id_pais,id_produccion,cantidad,id_partida) VALUES((SELECT max(turno) FROM Turnos WHERE id_partida=(SELECT MAX(id_partida)FROM Partidas)),(SELECT id_pais FROM Paises WHERE nombre='"+pais.getNombre()+"'),SELECT id_produccion FROM Producciones WHERE id_pais='"+pais.getNombre()+"' and id_turno=(SELECT max(turno) FROM Turnos WHERE id_partida=(SELECT MAX(id_partida)FROM Partidas)) and producto ='"+producto+"' and id_partida=(SELECT max(id_partida) FROM Partidas)),cantidad,(SELECT max(id_partida) FROM Partidas));
        //SELECT id_produccion FROM Producciones WHERE id_pais=(SELECT id_pais FROM Paises WHERE nombre='Castilla') and id_turno=(SELECT max(turno) FROM Turnos WHERE id_partida=(SELECT MAX(id_partida)FROM Partidas)) and producto ='Uvas' and id_partida=(SELECT max(id_partida) FROM Partidas)
    }

    public void insertarMercanciasFlotas(Reinos reino, int idMercancia){
        SQLiteDatabase db;
        Mercancia mercancia;
        db=this.getWritableDatabase();
        mercancia=reino.getMercancia().get(idMercancia);
        System.out.println(mercancia.getTotalkg());
        db.execSQL("INSERT INTO MercanciasFlota(id_flota,id_mercancia,id_turno,id_partida)VALUES ((SELECT id_flota FROM Flota WHERE id_pais=(select id_pais FROM Paises WHERE nombre='"+reino.getNombre()+"')), (SELECT id_mercancia FROM Mercancias WHERE id_turno=(SELECT max(turno) FROM Turnos WHERE id_partida=(SELECT MAX(id_partida)FROM Partidas)) AND id_pais=(SELECT id_pais FROM Paises WHERE nombre='"+reino.getNombre()+"') AND id_produccion=(SELECT id_produccion FROM Producciones WHERE id_pais=(SELECT id_pais FROM Paises WHERE nombre='"+reino.getNombre()+"') AND id_turno=(SELECT max(turno) FROM Turnos WHERE id_partida=(SELECT MAX(id_partida)FROM Partidas)) AND producto='"+mercancia.getProducto().getNombre().toString()+"'AND id_partida=(SELECT max(id_partida) FROM Partidas)) AND cantidad="+mercancia.getTotalkg()+" AND id_partida=(SELECT max(id_partida) FROM Partidas)), (SELECT max(turno) FROM Turnos WHERE id_partida=(SELECT MAX(id_partida)FROM Partidas)), (SELECT max(id_partida) FROM Partidas))");
    }

    public void updateDemandasRealizadas(Productos producto, String zona){
        SQLiteDatabase db;
        System.out.println(producto.getNombre().toString());
        System.out.println(zona);
        db=this.getWritableDatabase();
        db.execSQL("UPDATE Demandas SET realizada = 'true' WHERE id_demandas =(SELECT id_demandas FROM Demandas WHERE descripcion='"+producto.getNombre().toString()+"' and id_pais=(select id_pais FROM Paises WHERE nombre='"+zona+"')) and id_partida =(SELECT max(id_partida) FROM Partidas) and id_turno=(SELECT max(turno) FROM Turnos WHERE id_partida=(SELECT MAX(id_partida)FROM Partidas))");
        //id_turno_y_id_partida= SELECT turno,id_partida FROM Turnos WHERE id_partida=MAX(id_partida)FROM Partidas;
        //id_pais=SELECT id_pais FROM Paises WHERE nombre=this.getNombre();
        //id_demandas=SELECT id_demandas FROM Demandas WHERE descripcion=this.productosDemandados[i] and id_pais=id_pais and id_partida =id_turno_y_id_partida and id_turno=id_turno_y_id_partida;
        //UPDATE Demandas SET realizada = 'true' WHERE id_demandas = id_demandas;
    }

    public void updateDemandasNoRealizadas(int contador){
        SQLiteDatabase db;
        db=this.getWritableDatabase();
        db.execSQL("UPDATE Demandas SET realizada='false' WHERE id_partida=(SELECT max(id_partida) FROM Partidas) and id_turno=("+contador+"-1)and realizada is null");
        //id_turno_y_id_partida= SELECT turno,id_partida FROM Turnos WHERE id_partida=MAX(id_partida)FROM Partidas;
        //id_pais=SELECT id_pais FROM Paises WHERE nombre=this.getNombre();
        //id_demandas=SELECT id_demandas FROM Demandas WHERE descripcion=this.productosDemandados[i] and id_pais=id_pais and id_partida =id_turno_y_id_partida and id_turno=id_turno_y_id_partida;
        //UPDATE Demandas SET realizada = 'true' WHERE id_demandas = id_demandas;
    }

    public void insertFlotaEnviada(Reinos reino ,Reinos destino,int distancia){
        SQLiteDatabase db;
        db=this.getWritableDatabase();
        db.execSQL("INSERT INTO FlotaEnviada(id_flota,cantidad_almacenada,id_partida,id_turno,pais_envio,distancia_destino)VALUES((SELECT id_flota FROM Flota WHERE id_pais=(SELECT id_pais FROM Paises WHERE nombre='"+reino.getNombre()+"') AND id_partida=(SELECT max(id_partida) FROM Partidas)),"+reino.getFlota().getPesoMaximo()+",(SELECT max(id_partida) FROM Partidas),(SELECT max(turno) FROM Turnos WHERE id_partida=(SELECT MAX(id_partida)FROM Partidas)),(SELECT id_pais FROM Paises WHERE nombre='"+destino.getNombre()+"'),"+distancia+")");
        //"INSERT INTO FlotaEnviada(id_flota,cantidad_almacenada,id_partida,id_turno,pais_envio,distancia_destino)VALUES((SELECT id_flota FROM Flota WHERE id_pais=(SELECT id_pais FROM Paises WHERE nombre='"+reino.getNombre()+"') AND id_partida=(SELECT max(id_partida) FROM Partidas)),"+reino.getFlota().getPesoMaximo()+",(SELECT max(id_partida) FROM Partidas),(SELECT max(turno) FROM Turnos WHERE id_partida=(SELECT MAX(id_partida)FROM Partidas)),(SELECT id_pais FROM Paises WHERE nombre='"+destino.getNombre()+"'),"+distancia+")"

    }

    public void insertFlotasDevueltas(String reino,String devuelta){
        SQLiteDatabase db;
        db=this.getWritableDatabase();
        db.execSQL("INSERT INTO FlotasDevueltas(id_turno,id_flota,devuelta,id_partida)VALUES((SELECT max(turno) FROM Turnos WHERE id_partida=(SELECT MAX(id_partida)FROM Partidas)),(SELECT id_flota FROM Flota WHERE id_pais=(SELECT id_pais FROM Paises WHERE nombre='"+reino+"') AND id_partida=(SELECT max(id_partida) FROM Partidas)),'"+devuelta+"',(SELECT max(id_partida) FROM Partidas))");
        System.out.println("Se ha introducido correctamente");
    }

    public void insertarSublevaciones(String pais){
        SQLiteDatabase db;
        String hora = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        db=this.getWritableDatabase();
        db.execSQL("INSERT INTO Sublevaciones(id_pais,hora,id_turno,id_partida)VALUES((SELECT id_pais FROM Paises WHERE nombre='"+pais+"'),'"+hora+"',(SELECT max(turno) FROM Turnos WHERE id_partida=(SELECT MAX(id_partida)FROM Partidas)),(SELECT max(id_partida) FROM Partidas))");
    }

    public ArrayList<String> selectPartidas(){
        SQLiteDatabase db;
        ArrayList<String>partidas = new ArrayList<>();
        Cursor cursor;
        db=this.getReadableDatabase();

        cursor=db.rawQuery("SELECT usuario FROM Partidas ",null);

        while(cursor.moveToNext()){
            partidas.add(cursor.getString(0));
        }
        return partidas;
    }
}
