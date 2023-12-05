package com.ipartek.modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ipartek.modelo.dto.Discos;
import com.ipartek.modelo.dto.Grupos;
import com.ipartek.modelo.dto.V_Discos;


public class DB_Helper implements DAO_Constantes{
	
	public Connection Conectar() {
		
		Connection con=null;
		
		try {
			Class.forName(DRIVER);
			con= DriverManager.getConnection(RUTA,USUARIO,PASS);
		} catch (ClassNotFoundException e) {
			System.out.println("DRIVER NO ENCONTRADO");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("NO SE PUDO CONECTAR A LA BD");
			e.printStackTrace();
		}
		
		return con;
		
	}
	
	
	public List<V_Discos> obtenerTodoslosDiscos(Connection con) {
		
		//1 crear una lista vacía para ir rellenando y cuando este, devolver
		List<V_Discos> lista=new ArrayList<V_Discos>();
		
		try {
			//2 preparar la sentencia SQL para lanzar la consulta
			CallableStatement cStmt=con.prepareCall(SP_OBTENER_DISCOS);
			
			//3 ejecutar la sentencia para poder tener los resultados
			cStmt.execute();
			
			//4 el cStmt.getResultset lo guardamos en una variable
			//para poder trabajar comodamente
			ResultSet rs=cStmt.getResultSet();
			
			//5 el resultset siempre se recorre con un while
			while(rs.next()) {
				//5.1 creamos un objeto vacio para rellenarlo
				V_Discos disco=new V_Discos();
				
				//5.2 el objeto vacio se rellena con los set y
				//los datos del resultset
				//se obtienen TODOS LOS DATOS; aunque no los usemos luego
				disco.setId(rs.getInt(V_DISCOS_ID));
				disco.setTitulo(rs.getString(V_DISCOS_TITULO));
				disco.setNum_canciones(rs.getInt(V_DISCOS_NUM_CANCIONES));
				disco.setFk_grupo(rs.getInt(V_DISCOS_FK_GRUPO));
				disco.setGrupo(rs.getString(V_DISCOS_GRUPO));
				
				//5.3 el objeto relleno lo metemos en la lista
				lista.add(disco);
				
			}
			
			
		} catch (Exception e) {
			//6 si hay error la lista la vaciamos.
			System.out.println("NO SE PUDO OBTENER LA LISTA DE DISCOS");
			lista=new ArrayList<V_Discos>();
			
			e.printStackTrace();
			
		}
		
		//7 devolvemos la lista, si esta vacia, es que no hay bocadillos o
		//hubo un error, si viene llena, se realizo la consulta ok
		return lista;
	}
	
	public List<Discos> obtenerDiscos(Connection con) {
		List<Discos> lista=new ArrayList<Discos>();
		
		try {
			CallableStatement cStmt=con.prepareCall(SP_DISCOS);
			cStmt.execute();
			ResultSet rs=cStmt.getResultSet();
			
			while(rs.next()) {
				Discos disco=new Discos();
				disco.setId(rs.getInt(DISCOS_ID));
				disco.setTitulo(rs.getString(DISCOS_TITULO));
				disco.setNum_canciones(rs.getInt(DISCOS_NUM_CANCIONES));
				disco.setFk_grupo(rs.getInt(DISCOS_FK_GRUPO));

				lista.add(disco);
				
			}
			
			
		} catch (Exception e) {
			System.out.println("NO SE PUDO OBTENER LA LISTA DE DISCOS");
			lista=new ArrayList<Discos>();
			
			e.printStackTrace();
			
		}
		return lista;
	}



	public void desconectar(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			System.out.println("NO SE PUDO DESCONECTAR");
			e.printStackTrace();
		}
	}


	public void agregarDisco(Connection con, Discos disco) {
		
		try {

			CallableStatement cStmt = con.prepareCall(SP_INSERTAR_DISCO);
				cStmt.setString(1, disco.getTitulo());
				cStmt.setInt(2, disco.getNum_canciones());
				cStmt.setInt(3, disco.getFk_grupo());
				
			cStmt.execute();
		
			System.out.println("SE INSERTO EL DISCO CORRECTAMENTE");
		
		} catch (SQLException e) {
			System.out.println("NO SE PUDO INSERTAR EL DISCO");
			e.printStackTrace();
		}
		
		
	}


	public List<Grupos> obtenerTodoslosGrupos(Connection con) {
		//1 crear una lista vacía para ir rellenando y cuando este, devolver
		List<Grupos> lista=new ArrayList<Grupos>();
		try {
			//2 preparar la sentencia SQL para lanzar la consulta
			CallableStatement cStmt=con.prepareCall(SP_OBTENER_GRUPOS);
					
			//3 ejecutar la sentencia para poder tener los resultados
			cStmt.execute();
					
			//4 el cStmt.getResultset lo guardamos en una variable
			//para poder trabajar comodamente
			ResultSet rs=cStmt.getResultSet();
					
			//5 el resultset siempre se recorre con un while
			while(rs.next()) {
				Grupos grupo=new Grupos();
						
				grupo.setId(rs.getInt(GRUPOS_ID));
				grupo.setGrupo(rs.getString(GRUPOS_GRUPO));
						
				//5.3 el objeto relleno lo metemos en la lista
				lista.add(grupo);
			}
					
					
			} catch (Exception e) {
				//6 si hay error la lista la vaciamos.
				System.out.println("NO SE PUDO OBTENER LA LISTA DE GRUPOS");
				lista=new ArrayList<Grupos>();
					
				e.printStackTrace();
					
			}
				
			return lista;
		}
	
		public boolean borrardisco(Connection con, int disco) {
			boolean exito = false;
			try {
				CallableStatement cStmt = con.prepareCall(SP_BORRAR_DISCO);
	
				cStmt.setInt(1, disco);
				
				int filasAfectadas = cStmt.executeUpdate();
		        
		        cStmt.close();
		        
		        if (filasAfectadas > 0) {
		            exito = true;
		        }
		        
		    } catch (Exception e) {
		        System.out.println("NO SE PUDO BORRAR EL DISCO");
		        e.printStackTrace();
		    }
		return exito;
	}


		public void modificar_disco(Connection con, Discos disco) {
			
			try {
				CallableStatement cStmt = con.prepareCall(SP_MODIFICAR_DISCO);
				
				
				cStmt.setInt(1, disco.getId());
		        cStmt.setString(2, disco.getTitulo());
		        cStmt.setInt(3, disco.getNum_canciones());
		        cStmt.setInt(4, disco.getFk_grupo());
		        
		        cStmt.execute();
		        
		        cStmt.close();
		        
		    } catch (Exception e) {
		        System.out.println("NO SE PUDO MODIFICAR EL DISCO");
		        e.printStackTrace();
		    }
			
		}


		public V_Discos obtener_disco_por_id(Connection con, int id) {
			
			V_Discos disco = new V_Discos();
			try {
				CallableStatement cStmt = con.prepareCall(SP_OBTENER_DISCO_POR_ID);
				
				cStmt.setInt(1, id);
		        
		        cStmt.execute();
		        
		        ResultSet rs=cStmt.getResultSet();
				
				while(rs.next()) {
					disco.setId(rs.getInt(V_DISCOS_ID));
					disco.setTitulo(rs.getString(V_DISCOS_TITULO));
					disco.setNum_canciones(rs.getInt(V_DISCOS_NUM_CANCIONES));
					disco.setFk_grupo(rs.getInt(V_DISCOS_FK_GRUPO));
					disco.setGrupo(rs.getString(V_DISCOS_GRUPO));
				}
		        
		        cStmt.close();
		        
		    } catch (Exception e) {
		        disco=new V_Discos();
		        e.printStackTrace();
		    }
			
			return disco;
		}


		public void restaurarFila(Connection con, Grupos grupo) {
			
			try {
				CallableStatement cStmt = con.prepareCall(SP_RESTAURAR_GRUPO);
				
				cStmt.setInt(1, grupo.getId());
				cStmt.setString(2, grupo.getGrupo());
				
		        cStmt.execute();
		        
		        System.out.println("SE INCORPORÓ EL GRUPO CORRECTAMENTE");
		        
		    } catch (Exception e) {
		    	System.out.println("NO SE PUDO INSERTAR EL GRUPO");
		        e.printStackTrace();
		    }
			
		}
		
		public void borrarBaseDatos(Connection con) {
			
			try {
				CallableStatement cStmt = con.prepareCall(SP_BORRAR_BD);
			
		        cStmt.execute();
		        
		        System.out.println("SE BORRARON LAS TABLAS");
		        
		    } catch (Exception e) {
		    	System.out.println("NO SE PUDO BORRAR LAS TABLAS");
		        e.printStackTrace();
		    }
			
		}
		
		public void restaurarFilaDiscos(Connection con, Discos disco) {

			try {
				CallableStatement cStmt = con.prepareCall(SP_RESTAURAR_DISCO);

				cStmt.setInt(1, disco.getId());
			    cStmt.setString(2,disco.getTitulo() );
			    cStmt.setInt(3,disco.getNum_canciones() );
			    cStmt.setInt(4,disco.getFk_grupo() );
			  
			   cStmt.execute();
			  
			   System.out.println("SE INSERTO EL GRUPO CORRECTAMENTE");
			  
			  } catch (SQLException e) {
			   System.out.println("NO SE PUDO INSERTAR EL GRUPO");
			   e.printStackTrace();
			  
			  
			  }
		}
		
	
}
