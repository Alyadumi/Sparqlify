package org.aksw.sparqlify.algebra.sql.nodes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.aksw.sparqlify.algebra.sql.exprs2.SqlExpr;
import org.aksw.sparqlify.core.TypeToken;
import org.aksw.sparqlify.core.sql.schema.SchemaImpl;


public class Projection {
	private List<String> names = new ArrayList<String>();
	private Map<String, SqlExpr> nameToExpr = new HashMap<String, SqlExpr>();
	
	public Projection() {
		
	}

	public Projection(Map<String, SqlExpr> nameToExpr) {
		this.nameToExpr = nameToExpr;
		this.names = new ArrayList<String>(nameToExpr.keySet());
	}
	
	public Projection(List<String> names, Map<String, SqlExpr> nameToExpr) {
		this.names = names;
		this.nameToExpr = nameToExpr;
	}
	
	public Map<String, TypeToken> getTypeMap() {
		Map<String, TypeToken> result = new HashMap<String, TypeToken>();
		
		for(Entry<String, SqlExpr> entry : nameToExpr.entrySet()) {
			result.put(entry.getKey(), entry.getValue().getDatatype());
		}
		
		return result;
	}
	
	public boolean isEmpty() {
		boolean result = names.isEmpty();
		return result;
	}
	
	public void add(Projection other) {
		names.addAll(other.getNames());
		
		SchemaImpl.validateNames(names);
		
		nameToExpr.putAll(other.getNameToExpr());
	}
	
	/**
	 * In place projection
	 * 
	 * @param columnNames
	 */
	public void project(List<String> columnNames) {
		//this.names.retainAll(columnNames);
		this.names = columnNames;
		
		this.nameToExpr.keySet().retainAll(columnNames);
	}	

	public void extend(Projection other) {
		// TODO assert that there is no overlap in the column names
		
		this.names.addAll(other.getNames());
		SchemaImpl.validateNames(names);
		
		this.nameToExpr.putAll(other.getNameToExpr());
	}
	
	/**
	 * Create a new projection with only the given column names retained
	 * @param columnNames
	 */
	/*
	public Projection project(List<String> columnNames) {
		Map<String, Expr> newNameToExpr = new HashMap<String, Expr>();
		for(String name : columnNames) {
			Expr expr = nameToExpr.get(name);
			newNameToExpr.put(name, expr);
		}
		
		Projection result = new Projection(columnNames, newNameToExpr);
		
		
		return result;
	}*/
	
	public void rename(String oldName, String newName) {
		Collections.replaceAll(names, oldName, newName);
		
		SqlExpr val = nameToExpr.get(oldName);
		nameToExpr.remove(oldName);
		nameToExpr.put(newName, val);
	}
	
	/**
	 * In place rename
	 * 
	 * @param oldToNew
	 */
	public void renameAll(Map<String, String> oldToNew) {
		for(Entry<String, String> entry : oldToNew.entrySet()) {
			rename(entry.getKey(), entry.getValue());
		}
	}
	
	public void put(String name, SqlExpr expr) {
		if(!nameToExpr.containsKey(name)) {
			names.add(name);
		}
		nameToExpr.put(name, expr);
	}
	
	public List<String> getNames() {
		return names;
	}
	
	
	public Map<String, SqlExpr> getNameToExpr() {
		return nameToExpr;
	}


	@Override
	public String toString() {
		return "Projection [names=" + names + ", nameToExpr=" + nameToExpr
				+ "]";
	}
	
	
}


//class ProjectionMulti {
//    private Map<String, Projection> aliasToProjection;
//    
//    public void add(Projection projection, String alias) {
//        Projection tmp = aliasToProjection.get(alias);
//        if(tmp != null) {
//            throw new RuntimeException("Projection for alias " + alias + " already set.");
//        }
//        
//        
//        aliasToProjection.put(alias, projection);
//    }
//    
//    public Projection getProjection(String alias) {
//        return aliasToProjection.get(alias);
//    }
//}

