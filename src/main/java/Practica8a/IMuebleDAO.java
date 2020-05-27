/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practica8a;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jcarlosvico@maralboran.es
 */
public interface IMuebleDAO {
     
    boolean insertMueble(MuebleVO mueble) throws SQLException;
    
    int insertMueble(List<MuebleVO> lista) throws SQLException;
    
    boolean deleteMueble(MuebleVO mueble) throws SQLException;
    
    boolean updateMueble(String pk, MuebleVO mueble) throws SQLException;
    
    public List <MuebleVO> getAll() throws SQLException;
    
    MuebleVO findByPk(String pk) throws SQLException;
    
    
    
}
