/*
 * Clase que implementa la interface IPersona
 */
package Practica8a;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author J. Carlos F. Vico <jcarlosvico@maralboran.es>
 */
public class MuebleDAO implements IMuebleDAO {

    private Connection con = null;

    public MuebleDAO() {
        con = Conexion.getInstance();
    }

    @Override
    public List<MuebleVO> getAll() throws SQLException{
         List<MuebleVO> lista = new ArrayList<>();

        // Preparamos la consulta de datos mediante un objeto Statement
        // ya que no necesitamos parametrizar la sentencia SQL
        try (Statement st = con.createStatement()) {
            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet
            ResultSet res = st.executeQuery("select * from mueble");
            // Ahora construimos la lista, recorriendo el ResultSet y mapeando los datos
            while (res.next()) {
                MuebleVO p = new MuebleVO();
                // Recogemos los datos de la persona, guardamos en un objeto
                p.setId(res.getString("id"));
                p.setDescripcion(res.getString("descripcion"));
                p.setAncho(res.getDouble("ancho"));
                p.setAlto(res.getDouble("alto"));
                p.setProfundo(res.getDouble("profundo"));

                //Añadimos el objeto a la lista
                lista.add(p);
            }
        }

        return lista; 
    }

    @Override
    public MuebleVO findByPk(String pk) throws SQLException{
        ResultSet res = null;
        MuebleVO mueble = new MuebleVO();

        String sql = "select * from mueble where id=?";

        try (PreparedStatement prest = con.prepareStatement(sql)){
            // Preparamos la sentencia parametrizada
            prest.setString(1, pk);

            // Ejecutamos la sentencia y obtenemos las filas en el objeto ResultSet
            res = prest.executeQuery();

            // Nos posicionamos en el primer registro del Resultset. Sólo debe haber una fila
            // si existe esa pk
            if (res.next()) {
                // Recogemos los datos de la persona, guardamos en un objeto
                 mueble.setId(res.getString("id"));
                mueble.setDescripcion(res.getString("descripcion"));                
                mueble.setAncho(res.getDouble("ancho"));
                mueble.setAlto(res.getDouble("alto"));
                mueble.setProfundo(res.getDouble("profundo"));
                return mueble;
            }

            return null;
        }
    }
    
    @Override
    public boolean insertMueble(MuebleVO mueble) throws SQLException{
       
        boolean b= false;
        String sql = "insert into mueble values (?,?,?,?,?)";

        if (findByPk(mueble.getId()) != null) {

            return b;
        } else {

            try (PreparedStatement prest = con.prepareStatement(sql)) {

                // Establecemos los parámetros de la sentencia
                prest.setString(1, mueble.getId());
                prest.setString(2, mueble.getDescripcion());
                prest.setDouble(3, mueble.getAlto());
                prest.setDouble(4, mueble.getAncho());
                prest.setDouble(5, mueble.getProfundo());

                b=true;
            }            
            return b;
        }}

    @Override
    public int insertMueble(List<MuebleVO> lista) throws SQLException{
       int numFilas = 0;

        for (MuebleVO tmp : lista) {
            
            if(insertMueble(tmp)){
                numFilas++;
            }
        }

        return numFilas; 
    }

    @Override
    public boolean deleteMueble(MuebleVO mueble) throws SQLException{
         boolean b= false;

        String sql = "delete from mueble where id = ?";

        // Sentencia parametrizada
        try (PreparedStatement prest = con.prepareStatement(sql)) {

            // Establecemos los parámetros de la sentencia
            prest.setString(1, mueble.getId());
            // Ejecutamos la sentencia
           b=true;
        } 
        return b;

    }

    @Override
    public boolean updateMueble(String pk, MuebleVO mueble) throws SQLException{
       
        boolean b= false;
        String sql = "update mueble set descripcion = ?, ancho = ?, alto= ?, profundo= ?, where id=?";
        
        if (findByPk(pk) == null) {
            // La persona a actualizar no existe
            return b;
        } else {
            // Instanciamos el objeto PreparedStatement para inserción
            // de datos. Sentencia parametrizada
            try (PreparedStatement prest = con.prepareStatement(sql)) {

                // Establecemos los parámetros de la sentencia
               
                prest.setString(1, mueble.getDescripcion());             
                prest.setDouble(2, mueble.getAncho());
                prest.setDouble(3, mueble.getAlto());
                prest.setDouble(4, mueble.getProfundo());
                prest.setString(5, mueble.getId());
                 
                b= true;
            }
            return b;
        } 
    }

}

