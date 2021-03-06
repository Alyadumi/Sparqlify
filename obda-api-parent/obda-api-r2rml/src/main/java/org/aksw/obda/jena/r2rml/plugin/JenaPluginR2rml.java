package org.aksw.obda.jena.r2rml.plugin;

import org.aksw.jena_sparql_api.utils.model.SimpleImplementation;
import org.aksw.obda.jena.r2rml.domain.api.GraphMap;
import org.aksw.obda.jena.r2rml.domain.api.LogicalTable;
import org.aksw.obda.jena.r2rml.domain.api.ObjectMap;
import org.aksw.obda.jena.r2rml.domain.api.PredicateMap;
import org.aksw.obda.jena.r2rml.domain.api.PredicateObjectMap;
import org.aksw.obda.jena.r2rml.domain.api.SubjectMap;
import org.aksw.obda.jena.r2rml.domain.api.TermMap;
import org.aksw.obda.jena.r2rml.domain.api.TriplesMap;
import org.aksw.obda.jena.r2rml.domain.impl.GraphMapImpl;
import org.aksw.obda.jena.r2rml.domain.impl.LogicalTableImpl;
import org.aksw.obda.jena.r2rml.domain.impl.ObjectMapImpl;
import org.aksw.obda.jena.r2rml.domain.impl.PredicateMapImpl;
import org.aksw.obda.jena.r2rml.domain.impl.PredicateObjectMapImpl;
import org.aksw.obda.jena.r2rml.domain.impl.SubjectMapImpl;
import org.aksw.obda.jena.r2rml.domain.impl.TermMapImpl;
import org.aksw.obda.jena.r2rml.domain.impl.TriplesMapImpl;
import org.apache.jena.enhanced.BuiltinPersonalities;
import org.apache.jena.enhanced.Personality;
import org.apache.jena.rdf.model.RDFNode;

public class JenaPluginR2rml {
    
	public static void init() {
		JenaPluginR2rml.init(BuiltinPersonalities.model);		
	}
	
	public static void init(Personality<RDFNode> p) {
    	p.add(TriplesMap.class, new SimpleImplementation(TriplesMapImpl::new));
    	p.add(LogicalTable.class, new SimpleImplementation(LogicalTableImpl::new));
    	p.add(PredicateObjectMap.class, new SimpleImplementation(PredicateObjectMapImpl::new));
    	p.add(GraphMap.class, new SimpleImplementation(GraphMapImpl::new));
    	p.add(SubjectMap.class, new SimpleImplementation(SubjectMapImpl::new));
    	p.add(PredicateMap.class, new SimpleImplementation(PredicateMapImpl::new));
    	p.add(ObjectMap.class, new SimpleImplementation(ObjectMapImpl::new));
    	p.add(TermMap.class, new SimpleImplementation(TermMapImpl::new));
    }
}
