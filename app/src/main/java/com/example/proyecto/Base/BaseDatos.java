package com.example.proyecto.Base;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.QueryClase.QueryDemandas;
import com.example.QueryClase.QueryFlotasEnviadas;
import com.example.QueryClase.QueryMercancias;
import com.example.QueryClase.QueryMercanciasFlotas;
import com.example.QueryClase.QuerySublevaciones;
import com.example.proyecto.Clases.ProductoNombre;
import com.example.QueryClase.QueryProductos;
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

    public void borrarTablas(){
        SQLiteDatabase db;
        db=this.getWritableDatabase();
        db.execSQL("DROP TABLE MercanciasFlota");
        db.execSQL("DROP TABLE Mercancias");
        db.execSQL("DROP TABLE Producciones");
        db.execSQL("DROP TABLE FlotasDevueltas");
        db.execSQL("DROP TABLE FlotaEnviada");
        db.execSQL("DROP TABLE Demandas");
        db.execSQL("DROP TABLE Flota");
        db.execSQL("DROP TABLE Sublevaciones");
        db.execSQL("DROP TABLE Paises");
        db.execSQL("DROP TABLE Turnos");
        db.execSQL("DROP TABLE Partidas");
    }

    public void crearTablas(){
        SQLiteDatabase db;
        db=this.getWritableDatabase();
        db.execSQL("CREATE TABLE \"Partidas\" (\n" +
                "\t\"id_partida\"\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\t\"usuario\"\tTEXT,\n" +
                "\t\"turnos_jugados\" INTEGER,\n" +
                "\t\"hora_inicio\" TEXT,\n" +
                "\t\"hora_fin\" TEXT"+")");

        db.execSQL("CREATE TABLE \"Turnos\" (\n" +
                "\t\"id_turno\"\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\t\"turno\"\tINTEGER,\n" +
                "\t\"id_partida\" INTEGER,\n" +
                "\tFOREIGN KEY (id_partida) REFERENCES Partidas(id_partida)\n" +
                ")");

        db.execSQL("CREATE TABLE \"Paises\" (\n" +
                "\t\"id_pais\"\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\t\"nombre\"\tTEXT,\n" +
                "\t\"continente\"\tTEXT,\n" +
                "\t\"poblacion\" INTEGER"+")");

        db.execSQL("CREATE TABLE \"Sublevaciones\" (\n" +
                "\t\"id_sublevacion\"\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\t\"id_pais\"\tINTEGER,\n" +
                "\t\"hora\" TEXT,\n" +
                "\t\"id_turno\" INTEGER,\n" +
                "\t\"id_partida\" INTEGER,\n" +
                "\tFOREIGN KEY (id_pais) REFERENCES Paises(id_pais)\n," +
                "\tFOREIGN KEY (id_turno) REFERENCES Turnos(id_turno)\n," +
                "\tFOREIGN KEY (id_partida) REFERENCES Partidas(id_partida)\n" +
                ")");

        db.execSQL("\n" +
                "CREATE TABLE \"Flota\" (\n" +
                "\t\"id_flota\"\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\t\"id_pais\"\tINTEGER,\n" +
                "\t\"id_partida\" INTEGER,\n" +
                "\tFOREIGN KEY (id_pais) REFERENCES Paises(id_pais)\n," +
                "\tFOREIGN KEY (id_partida) REFERENCES Partidas(id_partida)\n" +
                ")");

        db.execSQL("CREATE TABLE \"Demandas\" (\n" +
                "\t\"id_demandas\"\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\t\"id_turno\"\tINTEGER,\n" +
                "\t\"descripcion\"\tTEXT,\n" +
                "\t\"id_pais\"\tINTEGER,\n" +
                "\t\"realizada\"\tINTEGER,\n" +
                "\t\"id_partida\" INTEGER,\n" +
                "\tFOREIGN KEY (id_pais) REFERENCES Paises(id_pais)\n," +
                "\tFOREIGN KEY (id_turno) REFERENCES Turnos(id_turno)\n," +
                "\tFOREIGN KEY (id_partida) REFERENCES Partidas(id_partida)\n" +
                ")");

        db.execSQL("CREATE TABLE \"FlotaEnviada\" (\n" +
                "\t\"id_flota_enviada\"\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\t\"id_flota\"\tINTEGER,\n" +
                "\t\"cantidad_almacenada\" INTEGER,\n" +
                "\t\"id_partida\" INTEGER,\n" +
                "\t\"id_turno\" INTEGER,\n" +
                "\t\"pais_envio\" INTEGER,\n" +
                "\t\"distancia_destino\" INTEGER,\n" +
                "\tFOREIGN KEY (id_flota) REFERENCES Flota(id_flota)\n," +
                "\tFOREIGN KEY (pais_envio) REFERENCES Paises(id_pais)\n," +
                "\tFOREIGN KEY (id_turno) REFERENCES Turnos(id_turno)\n," +
                "\tFOREIGN KEY (id_partida) REFERENCES Partidas(id_partida)\n" +
                ")");

        db.execSQL("CREATE TABLE \"FlotasDevueltas\" (\n" +
                "\t\"id_flotas_devueltas\"\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\t\"id_turno\" INTEGER,\n" +
                "\t\"id_flota\"\tINTEGER,\n" +
                "\t\"devuelta\" TEXT,\n" +
                "\t\"id_partida\" INTEGER,\n" +
                "\tFOREIGN KEY (id_flota) REFERENCES Flota(id_flota)\n," +
                "\tFOREIGN KEY (id_turno) REFERENCES Turnos(id_turno)\n," +
                "\tFOREIGN KEY (id_partida) REFERENCES Partidas(id_partida)\n" +
                ")");

        db.execSQL("CREATE TABLE \"Producciones\" (\n" +
                "\t\"id_produccion\"\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\t\"id_pais\"\tINTEGER,\n" +
                "\t\"id_turno\" INTEGER,\n" +
                "\t\"producto\"\tTEXT,\n" +
                "\t\"cantidad\"\tINTEGER,\n" +
                "\t\"id_partida\" INTEGER,\n" +
                "\tFOREIGN KEY (id_pais) REFERENCES Paises(id_pais)\n," +
                "\tFOREIGN KEY (id_turno) REFERENCES Turnos(id_turno)\n," +
                "\tFOREIGN KEY (id_partida) REFERENCES Partidas(id_partida)\n" +
                ")");

        db.execSQL("CREATE TABLE \"Mercancias\" (\n" +
                "\t\"id_mercancia\"\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\t\"id_turno\" INTEGER,\n" +
                "\t\"id_pais\"\tTEXT,\n" +
                "\t\"id_produccion\"\tINTEGER,\n" +
                "\t\"cantidad\"\tINTEGER,\n" +
                "\t\"id_partida\" INTEGER,\n" +
                "\tFOREIGN KEY (id_produccion) REFERENCES Producciones(id_produccion)\n," +
                "\tFOREIGN KEY (id_pais) REFERENCES Paises(id_pais)\n," +
                "\tFOREIGN KEY (id_turno) REFERENCES Turnos(id_turno)\n," +
                "\tFOREIGN KEY (id_partida) REFERENCES Partidas(id_partida)\n" +
                ")");

        db.execSQL("CREATE TABLE \"MercanciasFlota\" (\n" +
                "\t\"id_mercancia_flota\"\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\t\"id_flota\"\tINTEGER,\n" +
                "\t\"id_mercancia\" INTEGER,\n" +
                "\t\"id_turno\" INTEGER,\n" +
                "\t\"id_partida\" INTEGER,\n" +
                "\tFOREIGN KEY (id_mercancia) REFERENCES Mercancias(id_mercancia)\n," +
                "\tFOREIGN KEY (id_turno) REFERENCES Turnos(id_turno)\n," +
                "\tFOREIGN KEY (id_partida) REFERENCES Partidas(id_partida)\n" +
                ")");
    }

    public void borrarDatos(){
        SQLiteDatabase db;
        db=this.getWritableDatabase();
        db.execSQL("DELETE FROM MercanciasFlota");
        db.execSQL("DELETE FROM Mercancias");
        db.execSQL("DELETE FROM Producciones");
        db.execSQL("DELETE FROM FlotasDevueltas");
        db.execSQL("DELETE FROM FlotaEnviada");
        db.execSQL("DELETE FROM Demandas");
        db.execSQL("DELETE FROM Flota");
        db.execSQL("DELETE FROM Sublevaciones");
        db.execSQL("DELETE FROM Paises");
        db.execSQL("DELETE FROM Turnos");
        db.execSQL("DELETE FROM Partidas");
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

    public ArrayList<QueryProductos> selectProductos(String partida){
        SQLiteDatabase db;
        ArrayList<QueryProductos>productos = new ArrayList<>();
        Cursor cursor;
        db=this.getReadableDatabase();

        cursor=db.rawQuery("SELECT Paises.nombre,Producciones.id_turno,Producciones.producto,Producciones.cantidad,Partidas.usuario FROM Producciones,Paises,Partidas WHERE Producciones.id_pais=Paises.id_pais and Producciones.id_partida=Partidas.id_partida and Partidas.usuario='"+partida+"'",null);

        while(cursor.moveToNext()){
            String paiss =cursor.getString(0);
            int turn =cursor.getInt(1);
            String produ=cursor.getString(2);
            int cantida=cursor.getInt(3);
            //Problemas columna 5
            String user=cursor.getString(4);
            productos.add(new QueryProductos(paiss,turn+"",produ,cantida+"",user));
            //productos.add(new QueryProductos(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5)));
        }
        return productos;
    }

    public ArrayList<QueryDemandas> selectDemandas(String partida){
        SQLiteDatabase db;
        ArrayList<QueryDemandas>productos = new ArrayList<>();
        Cursor cursor;
        db=this.getReadableDatabase();

        cursor=db.rawQuery("SELECT Demandas.descripcion,Paises.nombre,Demandas.realizada,Partidas.usuario FROM Demandas,Paises,Partidas WHERE Demandas.id_pais=Paises.id_pais and Demandas.id_partida=Partidas.id_partida and Partidas.usuario='"+partida+"';",null);

        while(cursor.moveToNext()){
            String descripcion =cursor.getString(0);
            String pais =cursor.getString(1);
            String realizada=cursor.getString(2);
            String usuario=cursor.getString(3);

            productos.add(new QueryDemandas(descripcion,pais,realizada,usuario));
        }
        return productos;
    }

    public ArrayList<QueryMercancias> selectMercancias(String partida){
        SQLiteDatabase db;
        ArrayList<QueryMercancias>productos = new ArrayList<>();
        Cursor cursor;
        db=this.getReadableDatabase();

        cursor=db.rawQuery("SELECT Producciones.producto,Paises.nombre,Producciones.cantidad,Mercancias.cantidad,Mercancias.id_turno FROM Mercancias,Producciones,Paises,Partidas WHERE Mercancias.id_produccion=Producciones.id_produccion and Mercancias.id_pais=Paises.id_pais and Mercancias.id_partida=Partidas.id_partida and Partidas.usuario='"+partida+"'",null);

        cursorFiltroMercancias(productos, cursor);
        return productos;
    }

    public ArrayList<QueryMercanciasFlotas> selectMercanciasFlota(String partida){
        SQLiteDatabase db;
        ArrayList<QueryMercanciasFlotas>productos = new ArrayList<>();
        Cursor cursor;
        db=this.getReadableDatabase();

        cursor=db.rawQuery("SELECT Paises.nombre,Producciones.producto,Turnos.turno,Partidas.usuario FROM MercanciasFlota,Flota,Paises,Mercancias,Partidas,Producciones,Turnos WHERE MercanciasFlota.id_flota=Flota.id_flota and Flota.id_pais=Paises.id_pais and MercanciasFlota.id_mercancia=Mercancias.id_mercancia and Mercancias.id_produccion=Producciones.id_produccion and MercanciasFlota.id_turno=Turnos.id_turno and MercanciasFlota.id_partida=Partidas.id_partida and Partidas.usuario='"+partida+"'",null);

        while(cursor.moveToNext()){
            String paiss =cursor.getString(0);
            String mercan =cursor.getString(1);
            int turno=cursor.getInt(2);
            String partidaa=cursor.getString(3);
            //Problemas columna 5
            productos.add(new QueryMercanciasFlotas(paiss,mercan,turno+"",partidaa));
            //productos.add(new QueryProductos(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5)));
        }
        return productos;
    }

    public ArrayList<QueryFlotasEnviadas> selectFlotaEnviada(String partida){
        SQLiteDatabase db;
        ArrayList<QueryFlotasEnviadas>productos = new ArrayList<>();
        Cursor cursor;
        db=this.getReadableDatabase();

        cursor=db.rawQuery("SELECT origen.nombre as origen,FlotaEnviada.cantidad_almacenada,Turnos.turno,Partidas.usuario,destino.nombre as destino,FlotaEnviada.distancia_destino FROM FlotaEnviada,Paises AS origen,Paises AS destino,Flota,Partidas,Turnos where FlotaEnviada.id_flota=Flota.id_flota and Flota.id_pais=origen.id_pais and FlotaEnviada.id_partida=Partidas.id_partida and Partidas.usuario='"+partida+"' and FlotaEnviada.id_turno=Turnos.id_turno and FlotaEnviada.pais_envio=destino.id_pais\n",null);

        while(cursor.moveToNext()){
            String paiss =cursor.getString(0);
            int cantidad =cursor.getInt(1);
            int turno=cursor.getInt(2);
            String usuario=cursor.getString(3);
            String destino=cursor.getString(4);
            int distancia=cursor.getInt(5);

            productos.add(new QueryFlotasEnviadas(paiss,cantidad+"",usuario,turno+"",destino,distancia+""));
        }
        return productos;
    }

    public ArrayList<QuerySublevaciones> selectSublevaciones(String partida){
        SQLiteDatabase db;
        ArrayList<QuerySublevaciones>productos = new ArrayList<>();
        Cursor cursor;
        db=this.getReadableDatabase();

        cursor=db.rawQuery("SELECT Paises.nombre,Sublevaciones.hora,Turnos.turno,Partidas.usuario FROM Sublevaciones,Paises,Turnos,Partidas WHERE Sublevaciones.id_pais=Paises.id_pais and Sublevaciones.id_turno=Turnos.id_turno and Sublevaciones.id_partida=Partidas.id_partida and Partidas.usuario='"+partida+"'",null);

        while(cursor.moveToNext()){
            String paiss =cursor.getString(0);
            String hora =cursor.getString(1);
            int turno=cursor.getInt(2);
            String user=cursor.getString(3);

            productos.add(new QuerySublevaciones(paiss,hora,turno+"",user));
            //productos.add(new QueryProductos(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5)));
        }
        return productos;
    }

    //Métodos para los filtros

    //Mercancias

    public void cursorFiltroMercancias(ArrayList<QueryMercancias> productos, Cursor cursor) {
        while (cursor.moveToNext()) {
            String producto = cursor.getString(0);
            String pais = cursor.getString(1);
            int cantidadTotal = cursor.getInt(2);
            int cantidad = cursor.getInt(3);
            int turno = cursor.getInt(4);

            productos.add(new QueryMercancias(producto, pais, cantidadTotal + "", cantidad + "", turno + ""));
        }
    }

    public ArrayList<QueryMercancias> selectFiltroPais(String partida, String paisFiltro){
        SQLiteDatabase db;
        ArrayList<QueryMercancias>productos = new ArrayList<>();
        Cursor cursor;
        db=this.getReadableDatabase();

        cursor=db.rawQuery("SELECT Producciones.producto,Paises.nombre,Producciones.cantidad,Mercancias.cantidad,Mercancias.id_turno FROM Mercancias,Producciones,Paises,Partidas WHERE Mercancias.id_produccion=Producciones.id_produccion and Mercancias.id_pais=Paises.id_pais and Mercancias.id_partida=Partidas.id_partida and Partidas.usuario='"+partida+"' and Paises.Nombre = '"+paisFiltro+"'",null);

        cursorFiltroMercancias(productos, cursor);
        return productos;
    }

    public ArrayList<QueryMercancias> selectFiltroMercancia(String partida, String mercanciaFiltro){
        SQLiteDatabase db;
        ArrayList<QueryMercancias>productos = new ArrayList<>();
        Cursor cursor;
        db=this.getReadableDatabase();

        cursor=db.rawQuery("SELECT Producciones.producto,Paises.nombre,Producciones.cantidad,Mercancias.cantidad,Mercancias.id_turno FROM Mercancias,Producciones,Paises,Partidas WHERE Mercancias.id_produccion=Producciones.id_produccion and Mercancias.id_pais=Paises.id_pais and Mercancias.id_partida=Partidas.id_partida and Partidas.usuario='"+partida+"' and Producciones.producto = '"+mercanciaFiltro+"'",null);

        cursorFiltroMercancias(productos, cursor);
        return productos;
    }

    public ArrayList<QueryMercancias> selectFiltroTurno(String partida, String turno){
        SQLiteDatabase db;
        ArrayList<QueryMercancias>productos = new ArrayList<>();
        Cursor cursor;
        db=this.getReadableDatabase();

        cursor=db.rawQuery("SELECT Producciones.producto,Paises.nombre,Producciones.cantidad,Mercancias.cantidad,Mercancias.id_turno FROM Mercancias,Producciones,Paises,Partidas WHERE Mercancias.id_produccion=Producciones.id_produccion and Mercancias.id_pais=Paises.id_pais and Mercancias.id_partida=Partidas.id_partida and Partidas.usuario='"+partida+"' and Mercancias.id_turno = '"+turno+"'",null);

        cursorFiltroMercancias(productos, cursor);
        return productos;
    }

    //Flotas

    public void cursorFiltroFlotas(ArrayList<QueryMercanciasFlotas> productos, Cursor cursor) {
        while (cursor.moveToNext()) {
            String paiss = cursor.getString(0);
            String mercan = cursor.getString(1);
            int turno = cursor.getInt(2);
            String partidaa = cursor.getString(3);
            productos.add(new QueryMercanciasFlotas(paiss, mercan, turno + "", partidaa));
        }
    }

    public ArrayList<QueryMercanciasFlotas> selectFiltroFlotaMercancia(String partida, String mercancia){
        SQLiteDatabase db;
        ArrayList<QueryMercanciasFlotas>productos = new ArrayList<>();
        Cursor cursor;
        db=this.getReadableDatabase();

        cursor=db.rawQuery("SELECT Paises.nombre,Producciones.producto,Turnos.turno,Partidas.usuario FROM MercanciasFlota,Flota,Paises,Mercancias,Partidas,Producciones,Turnos WHERE MercanciasFlota.id_flota=Flota.id_flota and Flota.id_pais=Paises.id_pais and MercanciasFlota.id_mercancia=Mercancias.id_mercancia and Mercancias.id_produccion=Producciones.id_produccion and MercanciasFlota.id_turno=Turnos.id_turno and MercanciasFlota.id_partida=Partidas.id_partida and Partidas.usuario='"+partida+"' and Producciones.producto ='"+mercancia+"'",null);

        cursorFiltroFlotas(productos, cursor);
        return productos;
    }

    public ArrayList<QueryMercanciasFlotas> selectFiltroFlotaPais(String partida, String pais){
        SQLiteDatabase db;
        ArrayList<QueryMercanciasFlotas>productos = new ArrayList<>();
        Cursor cursor;
        db=this.getReadableDatabase();

        cursor=db.rawQuery("SELECT Paises.nombre,Producciones.producto,Turnos.turno,Partidas.usuario FROM MercanciasFlota,Flota,Paises,Mercancias,Partidas,Producciones,Turnos WHERE MercanciasFlota.id_flota=Flota.id_flota and Flota.id_pais=Paises.id_pais and MercanciasFlota.id_mercancia=Mercancias.id_mercancia and Mercancias.id_produccion=Producciones.id_produccion and MercanciasFlota.id_turno=Turnos.id_turno and MercanciasFlota.id_partida=Partidas.id_partida and Partidas.usuario='"+partida+"' and Paises.nombre = '"+pais+"'",null);

        cursorFiltroFlotas(productos, cursor);
        return productos;
    }

    public ArrayList<QueryMercanciasFlotas> selectFiltroFlotaTurno(String partida, String turnoNum){
        SQLiteDatabase db;
        ArrayList<QueryMercanciasFlotas>productos = new ArrayList<>();
        Cursor cursor;
        db=this.getReadableDatabase();

        cursor=db.rawQuery("SELECT Paises.nombre,Producciones.producto,Turnos.turno,Partidas.usuario FROM MercanciasFlota,Flota,Paises,Mercancias,Partidas,Producciones,Turnos WHERE MercanciasFlota.id_flota=Flota.id_flota and Flota.id_pais=Paises.id_pais and MercanciasFlota.id_mercancia=Mercancias.id_mercancia and Mercancias.id_produccion=Producciones.id_produccion and MercanciasFlota.id_turno=Turnos.id_turno and MercanciasFlota.id_partida=Partidas.id_partida and Partidas.usuario='"+partida+"' and Turnos.turno = '"+turnoNum+"'",null);

        cursorFiltroFlotas(productos, cursor);
        return productos;
    }

    public ArrayList<QueryMercanciasFlotas> selectFiltroFlotaPartida(String partida, String partidaNum){
        SQLiteDatabase db;
        ArrayList<QueryMercanciasFlotas>productos = new ArrayList<>();
        Cursor cursor;
        db=this.getReadableDatabase();

        cursor=db.rawQuery("SELECT Paises.nombre,Producciones.producto,Turnos.turno,Partidas.usuario FROM MercanciasFlota,Flota,Paises,Mercancias,Partidas,Producciones,Turnos WHERE MercanciasFlota.id_flota=Flota.id_flota and Flota.id_pais=Paises.id_pais and MercanciasFlota.id_mercancia=Mercancias.id_mercancia and Mercancias.id_produccion=Producciones.id_produccion and MercanciasFlota.id_turno=Turnos.id_turno and MercanciasFlota.id_partida=Partidas.id_partida and Partidas.usuario='"+partida+"' and Partidas.usuario = '"+partidaNum+"'",null);

        cursorFiltroFlotas(productos, cursor);
        return productos;
    }

    //Demandas

    public void cursorFiltroDemandas(ArrayList<QueryDemandas> productos, Cursor cursor) {
        while (cursor.moveToNext()) {
            String descripcion = cursor.getString(0);
            String pais = cursor.getString(1);
            String realizada = cursor.getString(2);
            String usuario = cursor.getString(3);

            productos.add(new QueryDemandas(descripcion, pais, realizada, usuario));
        }
    }

    public ArrayList<QueryDemandas> selectFiltroDemandasDescripcion(String partida, String producto){
        SQLiteDatabase db;
        ArrayList<QueryDemandas>productos = new ArrayList<>();
        Cursor cursor;
        db=this.getReadableDatabase();

        cursor=db.rawQuery("SELECT Demandas.descripcion,Paises.nombre,Demandas.realizada,Partidas.usuario FROM Demandas,Paises,Partidas WHERE Demandas.id_pais=Paises.id_pais and Demandas.id_partida=Partidas.id_partida and Partidas.usuario='"+partida+"' and Demandas.descripcion = '"+producto+"'",null);

        cursorFiltroDemandas(productos, cursor);
        return productos;
    }

    public ArrayList<QueryDemandas> selectFiltroDemandasPais(String partida, String paisNombre){
        SQLiteDatabase db;
        ArrayList<QueryDemandas>productos = new ArrayList<>();
        Cursor cursor;
        db=this.getReadableDatabase();

        cursor=db.rawQuery("SELECT Demandas.descripcion,Paises.nombre,Demandas.realizada,Partidas.usuario FROM Demandas,Paises,Partidas WHERE Demandas.id_pais=Paises.id_pais and Demandas.id_partida=Partidas.id_partida and Partidas.usuario='"+partida+"' and Paises.nombre = '"+paisNombre+"'",null);

        cursorFiltroDemandas(productos, cursor);
        return productos;
    }

    //Sublevaciones

    public void cursorFiltroSublevacion(ArrayList<QuerySublevaciones> productos, Cursor cursor) {
        while (cursor.moveToNext()) {
            String paiss = cursor.getString(0);
            String hora = cursor.getString(1);
            int turno = cursor.getInt(2);
            String user = cursor.getString(3);

            productos.add(new QuerySublevaciones(paiss, hora, turno + "", user));
        }
    }

    public ArrayList<QuerySublevaciones> selectFiltroSublevacionPais(String partida, String paisNombre){
        SQLiteDatabase db;
        ArrayList<QuerySublevaciones>productos = new ArrayList<>();
        Cursor cursor;
        db=this.getReadableDatabase();

        cursor=db.rawQuery("SELECT Paises.nombre,Sublevaciones.hora,Turnos.turno,Partidas.usuario FROM Sublevaciones,Paises,Turnos,Partidas WHERE Sublevaciones.id_pais=Paises.id_pais and Sublevaciones.id_turno=Turnos.id_turno and Sublevaciones.id_partida=Partidas.id_partida and Partidas.usuario='"+partida+"' and Paises.nombre = '"+paisNombre+"'",null);

        cursorFiltroSublevacion(productos, cursor);
        return productos;
    }

    public ArrayList<QuerySublevaciones> selectFiltroSublevacionTurno(String partida, String turnoNum){
        SQLiteDatabase db;
        ArrayList<QuerySublevaciones>productos = new ArrayList<>();
        Cursor cursor;
        db=this.getReadableDatabase();

        cursor=db.rawQuery("SELECT Paises.nombre,Sublevaciones.hora,Turnos.turno,Partidas.usuario FROM Sublevaciones,Paises,Turnos,Partidas WHERE Sublevaciones.id_pais=Paises.id_pais and Sublevaciones.id_turno=Turnos.id_turno and Sublevaciones.id_partida=Partidas.id_partida and Partidas.usuario='"+partida+"' and Turnos.turno = '"+turnoNum+"'",null);

        cursorFiltroSublevacion(productos, cursor);
        return productos;
    }

    //Producciones

    public void cursorFiltroProducciones(ArrayList<QueryProductos> productos, Cursor cursor) {
        while (cursor.moveToNext()) {
            String paiss = cursor.getString(0);
            int turn = cursor.getInt(1);
            String produ = cursor.getString(2);
            int cantida = cursor.getInt(3);
            String user = cursor.getString(4);
            productos.add(new QueryProductos(paiss, turn + "", produ, cantida + "", user));
        }
    }

    public ArrayList<QueryProductos> selectFiltroProduccionesPaises(String partida, String pais){
        SQLiteDatabase db;
        ArrayList<QueryProductos>productos = new ArrayList<>();
        Cursor cursor;
        db=this.getReadableDatabase();

        cursor=db.rawQuery("SELECT Paises.nombre,Producciones.id_turno,Producciones.producto,Producciones.cantidad,Partidas.usuario FROM Producciones,Paises,Partidas WHERE Producciones.id_pais=Paises.id_pais and Producciones.id_partida=Partidas.id_partida and Partidas.usuario='"+partida+"' and Paises.nombre = '"+pais+"'",null);

        cursorFiltroProducciones(productos, cursor);
        return productos;
    }

    public ArrayList<QueryProductos> selectFiltroProduccionesTurno(String partida, String turno){
        SQLiteDatabase db;
        ArrayList<QueryProductos>productos = new ArrayList<>();
        Cursor cursor;
        db=this.getReadableDatabase();

        cursor=db.rawQuery("SELECT Paises.nombre,Producciones.id_turno,Producciones.producto,Producciones.cantidad,Partidas.usuario FROM Producciones,Paises,Partidas WHERE Producciones.id_pais=Paises.id_pais and Producciones.id_partida=Partidas.id_partida and Partidas.usuario='"+partida+"' and Producciones.id_turno = '"+turno+"'",null);

        cursorFiltroProducciones(productos, cursor);
        return productos;
    }

    public ArrayList<QueryProductos> selectFiltroProduccionesPartida(String partida, String nPartida){
        SQLiteDatabase db;
        ArrayList<QueryProductos>productos = new ArrayList<>();
        Cursor cursor;
        db=this.getReadableDatabase();

        cursor=db.rawQuery("SELECT Paises.nombre,Producciones.id_turno,Producciones.producto,Producciones.cantidad,Partidas.usuario FROM Producciones,Paises,Partidas WHERE Producciones.id_pais=Paises.id_pais and Producciones.id_partida=Partidas.id_partida and Partidas.usuario='"+partida+"' and Partidas.usuario = '"+nPartida+"'",null);

        cursorFiltroProducciones(productos, cursor);
        return productos;
    }

    public ArrayList<QueryProductos> selectFiltroProduccionesProducto(String partida, String producto){
        SQLiteDatabase db;
        ArrayList<QueryProductos>productos = new ArrayList<>();
        Cursor cursor;
        db=this.getReadableDatabase();

        cursor=db.rawQuery("SELECT Paises.nombre,Producciones.id_turno,Producciones.producto,Producciones.cantidad,Partidas.usuario FROM Producciones,Paises,Partidas WHERE Producciones.id_pais=Paises.id_pais and Producciones.id_partida=Partidas.id_partida and Partidas.usuario='"+partida+"' and Producciones.producto = '"+producto+"'",null);

        cursorFiltroProducciones(productos, cursor);
        return productos;
    }

    // Flota enviada

    public void cursorFiltroFlotaEnviada(ArrayList<QueryFlotasEnviadas> productos, Cursor cursor) {
        while (cursor.moveToNext()) {
            String paiss = cursor.getString(0);
            int cantidad = cursor.getInt(1);
            int turno = cursor.getInt(2);
            String usuario = cursor.getString(3);
            String destino = cursor.getString(4);
            int distancia = cursor.getInt(5);

            productos.add(new QueryFlotasEnviadas(paiss, cantidad + "", usuario, turno + "", destino, distancia + ""));
        }
    }

    public ArrayList<QueryFlotasEnviadas> selectFiltroFlotaEnviadaPais(String partida, String destino){
        SQLiteDatabase db;
        ArrayList<QueryFlotasEnviadas>productos = new ArrayList<>();
        Cursor cursor;
        db=this.getReadableDatabase();

        cursor=db.rawQuery("SELECT origen.nombre as origen,FlotaEnviada.cantidad_almacenada,Turnos.turno,Partidas.usuario,destino.nombre as destino,FlotaEnviada.distancia_destino FROM FlotaEnviada,Paises AS origen,Paises AS destino,Flota,Partidas,Turnos where FlotaEnviada.id_flota=Flota.id_flota and Flota.id_pais=origen.id_pais and FlotaEnviada.id_partida=Partidas.id_partida and Partidas.usuario='"+partida+"' and FlotaEnviada.id_turno=Turnos.id_turno and FlotaEnviada.pais_envio=destino.id_pais and destino.nombre='"+destino+"'",null);

        cursorFiltroFlotaEnviada(productos, cursor);
        return productos;
    }

    public ArrayList<QueryFlotasEnviadas> selectFiltroFlotaEnviadaTurno(String partida, String turno){
        SQLiteDatabase db;
        ArrayList<QueryFlotasEnviadas>productos = new ArrayList<>();
        Cursor cursor;
        db=this.getReadableDatabase();

        cursor=db.rawQuery("SELECT origen.nombre as origen,FlotaEnviada.cantidad_almacenada,Turnos.turno,Partidas.usuario,destino.nombre as destino,FlotaEnviada.distancia_destino FROM FlotaEnviada,Paises AS origen,Paises AS destino,Flota,Partidas,Turnos where FlotaEnviada.id_flota=Flota.id_flota and Flota.id_pais=origen.id_pais and FlotaEnviada.id_partida=Partidas.id_partida and Partidas.usuario='"+partida+"' and FlotaEnviada.id_turno=Turnos.id_turno and FlotaEnviada.pais_envio=destino.id_pais and Turnos.turno = '"+turno+"'",null);

        cursorFiltroFlotaEnviada(productos, cursor);
        return productos;
    }

    public ArrayList<QueryFlotasEnviadas> selectFiltroFlotaEnviadaPartida(String partida, String nPartida){
        SQLiteDatabase db;
        ArrayList<QueryFlotasEnviadas>productos = new ArrayList<>();
        Cursor cursor;
        db=this.getReadableDatabase();

        cursor=db.rawQuery("SELECT origen.nombre as origen,FlotaEnviada.cantidad_almacenada,Turnos.turno,Partidas.usuario,destino.nombre as destino,FlotaEnviada.distancia_destino FROM FlotaEnviada,Paises AS origen,Paises AS destino,Flota,Partidas,Turnos where FlotaEnviada.id_flota=Flota.id_flota and Flota.id_pais=origen.id_pais and FlotaEnviada.id_partida=Partidas.id_partida and Partidas.usuario='"+partida+"' and FlotaEnviada.id_turno=Turnos.id_turno and FlotaEnviada.pais_envio=destino.id_pais and Partidas.usuario = '"+nPartida+"'",null);

        cursorFiltroFlotaEnviada(productos, cursor);
        return productos;
    }

}