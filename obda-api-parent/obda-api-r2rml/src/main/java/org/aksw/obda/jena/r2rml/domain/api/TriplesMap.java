package org.aksw.obda.jena.r2rml.domain.api;

import java.util.Set;

public interface TriplesMap
	extends MappingComponent
{
	SubjectMap getSubjectMap();
	TriplesMap setSubjectMap(SubjectMap subjectMap);
	
	
	Set<PredicateObjectMap> getPredicateObjectMaps();

	LogicalTable getLogicalTable();
	TriplesMap setLogicalTable(LogicalTable logicalTable);
}
