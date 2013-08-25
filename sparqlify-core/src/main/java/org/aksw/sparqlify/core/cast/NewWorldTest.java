package org.aksw.sparqlify.core.cast;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aksw.commons.util.MapReader;
import org.aksw.sparqlify.algebra.sparql.expr.E_RdfTerm;
import org.aksw.sparqlify.algebra.sparql.transform.MethodSignature;
import org.aksw.sparqlify.algebra.sql.exprs.evaluators.SqlExprEvaluator;
import org.aksw.sparqlify.algebra.sql.exprs.evaluators.SqlExprEvaluator_Arithmetic;
import org.aksw.sparqlify.algebra.sql.exprs.evaluators.SqlExprEvaluator_Compare;
import org.aksw.sparqlify.algebra.sql.exprs.evaluators.SqlExprEvaluator_LogicalAnd;
import org.aksw.sparqlify.algebra.sql.exprs.evaluators.SqlExprEvaluator_LogicalNot;
import org.aksw.sparqlify.algebra.sql.exprs.evaluators.SqlExprEvaluator_LogicalOr;
import org.aksw.sparqlify.algebra.sql.exprs.evaluators.SqlExprEvaluator_ParseInt;
import org.aksw.sparqlify.algebra.sql.exprs.evaluators.SqlExprEvaluator_PassThrough;
import org.aksw.sparqlify.algebra.sql.exprs.evaluators.SqlExprEvaluator_SqlRewrite;
import org.aksw.sparqlify.algebra.sql.exprs2.ExprSqlBridge;
import org.aksw.sparqlify.algebra.sql.exprs2.S_Constant;
import org.aksw.sparqlify.algebra.sql.exprs2.SqlExpr;
import org.aksw.sparqlify.core.RdfViewSystemOld;
import org.aksw.sparqlify.core.SparqlifyConstants;
import org.aksw.sparqlify.core.TypeToken;
import org.aksw.sparqlify.core.algorithms.DatatypeToStringPostgres;
import org.aksw.sparqlify.core.algorithms.ExprEvaluator;
import org.aksw.sparqlify.core.algorithms.ExprSqlRewrite;
import org.aksw.sparqlify.core.datatypes.SparqlFunction;
import org.aksw.sparqlify.core.datatypes.SparqlFunctionImpl;
import org.aksw.sparqlify.core.transformations.ExprTransformer;
import org.aksw.sparqlify.core.transformations.RdfTermEliminatorImpl;
import org.aksw.sparqlify.core.transformations.SqlTranslationUtils;
import org.aksw.sparqlify.expr.util.NodeValueUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Multimap;
import com.hp.hpl.jena.datatypes.RDFDatatype;
import com.hp.hpl.jena.datatypes.TypeMapper;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.sparql.core.Var;
import com.hp.hpl.jena.sparql.expr.Expr;
import com.hp.hpl.jena.sparql.expr.NodeValue;
import com.hp.hpl.jena.sparql.util.ExprUtils;
import com.hp.hpl.jena.vocabulary.XSD;
import com.thoughtworks.xstream.io.binary.Token;

/* This is the SqlExprTransformer
 interface ExprTypeEvaluator {
 SqlExpr eval(Expr expr);
 }

 class ExprTypeEvaluatorImpl
 implements ExprTypeEvaluator
 {
 @Override
 public SqlExpr eval(Expr expr) {
 //ExprEvaluato

 // TODO Auto-generated method stub
 return null;
 }

 }
 */




/*
 class ExprEvaluatorJena
 implements ExprEvaluator
 {
 @Override
 public Expr eval(Expr expr) {
 Expr result = ExprUtils.eval(expr);
 return result;
 }

 @Override
 public Expr eval(Expr expr, Map<Var, Expr> binding) {
 // TODO Auto-generated method stub
 return null;
 }
 }
 */

class SparqlEvaluatorChain implements SqlExprEvaluator {
	@Override
	public SqlExpr eval(List<SqlExpr> args) {
		// TODO Auto-generated method stub
		return null;
	}

}

class SparqlEvaluatorDefault implements SqlExprEvaluator {
	@Override
	public SqlExpr eval(List<SqlExpr> args) {

		// TODO Auto-generated method stub
		return null;
	}
}

class SparqlEvaluatorTypeSystem implements SqlExprEvaluator {

	@Override
	public SqlExpr eval(List<SqlExpr> args) {
		// TODO Auto-generated method stub
		return null;
	}

}


// The question is, whether a SqlValue transformer should be a
// subClassOf SqlExprEvaluator.
interface Foo
	extends SqlExprEvaluator
{
	SqlExpr eval(List<SqlExpr> args);
}


interface SqlValueTransformer {
	SqlValue transform(SqlValue nodeValue) throws CastException;
}

class SqlValueTransformerInteger
	implements SqlValueTransformer
{
	/**
	 * TODO The transformer should be able to cast to specific subtypes of int
	 * so: How to handle e.g. the case: (int2)1234 ???
	 * 
	 * In the generic case, we would need something such as e.g.
	 * castToInt(int byteCount, Object value)
	 * 
	 * @param sqlValue
	 * @return
	 * @throws CastException
	 */
	@Override
	public SqlValue transform(SqlValue sqlValue) throws CastException {
		
		
		String str = "" + sqlValue.getValue(); 
		//TypeMapper tm = TypeMapper.getInstance();

		//String typeName = TypeToken.Int.toString();
		Long v;
		try {
			v = Long.parseLong(str);
		} catch(NumberFormatException e) {
			throw new CastException("Could not cast " + str + " to integer");
		}
		
		
		SqlValue result = new SqlValue(TypeToken.Long, v);
		// String typeName = XSD.integer.toString();
		//RDFDatatype dt = tm.getSafeTypeByName(typeName);

		//Node node = Node.createLiteral(str, dt);
		//NodeValue result = NodeValue.makeNode(node);
		return result;
	}
}


class SqlValueTransformerFloat
	implements SqlValueTransformer
{
	/**
	 * TODO The transformer should be able to cast to specific subtypes of int
	 * so: How to handle e.g. the case: (int2)1234 ???
	 * 
	 * In the generic case, we would need something such as e.g.
	 * castToInt(int byteCount, Object value)
	 * 
	 * @param sqlValue
	 * @return
	 * @throws CastException
	 */
	@Override
	public SqlValue transform(SqlValue sqlValue) throws CastException {
		
		
		String str = "" + sqlValue.getValue(); 
		//TypeMapper tm = TypeMapper.getInstance();
	
		//String typeName = TypeToken.Int.toString();
		Float v;
		try {
			v = Float.parseFloat(str);
		} catch(NumberFormatException e) {
			throw new CastException("Could not cast " + str + " to float");
		}
		
		
		SqlValue result = new SqlValue(TypeToken.Float, v);
		// String typeName = XSD.integer.toString();
		//RDFDatatype dt = tm.getSafeTypeByName(typeName);
	
		//Node node = Node.createLiteral(str, dt);
		//NodeValue result = NodeValue.makeNode(node);
		return result;
	}
}


class NodeValueTransformerInteger implements NodeValueTransformer {
	@Override
	public NodeValue transform(NodeValue nodeValue) throws CastException {
		String str = "" + NodeValueUtils.getValue(nodeValue);
		TypeMapper tm = TypeMapper.getInstance();

		String typeName = TypeToken.Int.toString();
		// String typeName = XSD.integer.toString();
		RDFDatatype dt = tm.getSafeTypeByName(typeName);

		Node node = Node.createLiteral(str, dt);
		NodeValue result = NodeValue.makeNode(node);
		return result;
	}
}

/**
 * Greatly simplified design, which unifies SPARQL and SQL functions.
 * 
 * With this design, SqlExpr should be renamed to TypedExpr: It simply captures
 * the datatype which an expression yields.
 * 
 * 
 * 
 * 
 * @author Claus Stadler <cstadler@informatik.uni-leipzig.de>
 * 
 */
public class NewWorldTest {

	public static void registerSqlOperatorBatchNumeric(FunctionModel<TypeToken> sqlModel, String name) {
		sqlModel.registerFunction(name + "@boolean", name, MethodSignature.create(false, TypeToken.Boolean, TypeToken.Boolean, TypeToken.Boolean));
		sqlModel.registerFunction(name + "@int", name, MethodSignature.create(false, TypeToken.Int, TypeToken.Int, TypeToken.Int));
		sqlModel.registerFunction(name + "@float", name, MethodSignature.create(false, TypeToken.Float, TypeToken.Float, TypeToken.Float));
		sqlModel.registerFunction(name + "@double", name, MethodSignature.create(false, TypeToken.Double, TypeToken.Double, TypeToken.Double));
		//sqlModel.registerFunction(name + "@string", name, MethodSignature.create(false, TypeToken.String, TypeToken.String, TypeToken.String));
		//sqlModel.registerFunction(name + "@dateTime", name, MethodSignature.create(false, TypeToken.Date, TypeToken.Date, TypeToken.Date));		
	}

	public static void registerSqlOperatorBatchCompare(FunctionModel<TypeToken> sqlModel, String name) {
		sqlModel.registerFunction(name + "@boolean", name, MethodSignature.create(false, TypeToken.Boolean, TypeToken.Boolean, TypeToken.Boolean));
		sqlModel.registerFunction(name + "@int", name, MethodSignature.create(false, TypeToken.Boolean, TypeToken.Int, TypeToken.Int));
		sqlModel.registerFunction(name + "@float", name, MethodSignature.create(false, TypeToken.Boolean, TypeToken.Float, TypeToken.Float));
		sqlModel.registerFunction(name + "@double", name, MethodSignature.create(false, TypeToken.Boolean, TypeToken.Double, TypeToken.Double));
		sqlModel.registerFunction(name + "@string", name, MethodSignature.create(false, TypeToken.Boolean, TypeToken.String, TypeToken.String));
		sqlModel.registerFunction(name + "@dateTime", name, MethodSignature.create(false, TypeToken.Boolean, TypeToken.Date, TypeToken.Date));		
	}

	
	private static final Logger logger = LoggerFactory.getLogger(NewWorldTest.class);
	
	public static TypeSystem createDefaultDatatypeSystem() {

		// String basePath = "src/main/resources";
		try {
			Map<String, String> typeNameToClass = MapReader
					.readFromResource("/type-class.tsv");
			Map<String, String> typeNameToUri = MapReader
					.readFromResource("/type-uri.tsv");
			
			Map<String, String> typeHierarchy = MapReader
					.readFromResource("/type-hierarchy.default.tsv");

			Map<String, String> physicalTypeMap = MapReader
					.readFromResource("/type-map.h2.tsv");

			// TODO HACK Do not add types programmatically 
			physicalTypeMap.put("INTEGER", "int");
			physicalTypeMap.put("FLOAT", "float");
			
			//typeHierarchy.putAll(physicalTypeMap);
			
			
			TypeSystem result = TypeSystemImpl.create(typeHierarchy, physicalTypeMap);

			initSparqlModel(result);

			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static <K, V> void putForAll(Map<K, V> map, Collection<K> keys, V value) {
		for(K key : keys) {
			map.put(key, value);
		}
	}
	
	/**
	 * Create the SPARQL and SQL models
	 * 
	 * 
	 * 
	 */
	public static void initSparqlModel(TypeSystem typeSystem) {

		
		// NodeValue xxx = NodeValue.makeInteger(1);
		// RDFDatatype yyy = xxx.asNode().getLiteral().getDatatype();
		// System.out.println(yyy);

		S_Constant x;

		// SIGH, Jena does not support assigning custom URIs to its default
		// datatypes.
		// Register type token datatypes
		TypeMapper tm = TypeMapper.getInstance();
		String xxx = "http://mytype.org/foo/bar";

//		RDFDatatype inner = new XSDBaseNumericType("int", BigInteger.class);
//		RDFDatatype i = new RDFDatatypeCustomUri("int", inner);

		
		SqlTypeMapper stm = typeSystem.getSqlTypeMapper();
		stm.register(XSD.xstring.getURI(), new SqlDatatypeDefault(TypeToken.String, new NodeValueToObjectDefault()));
		stm.register(XSD.xboolean.getURI(), new SqlDatatypeDefault(TypeToken.Boolean, new NodeValueToObjectDefault()));

		stm.register(XSD.integer.getURI(),  new SqlDatatypeDefault(TypeToken.Int, new NodeValueToObjectDefault()));
		stm.register(XSD.decimal.getURI(),  new SqlDatatypeDefault(TypeToken.Int, new NodeValueToObjectDefault()));

		
		stm.register(XSD.dateTime.getURI(),  new SqlDatatypeDefault(TypeToken.Date, new NodeValueToObjectDefault()));

		
		stm.register(SparqlifyConstants.nvTypeError.asNode().getLiteralDatatypeURI(), new SqlDatatypeConstant(SqlValue.TYPE_ERROR));

		
		
		
		
		// RDFDatatype i = new XSDBaseNumericType(TypeToken.Int.toString(),
		// BigInteger.class);

//		tm.registerDatatype(i);

		CoercionSystemImpl3 cs = (CoercionSystemImpl3) typeSystem
				.getCoercionSystem();


		cs.registerCoercion(TypeToken.alloc(XSD.integer.toString()),
				TypeToken.Int, new SqlValueTransformerInteger());

		cs.registerCoercion(TypeToken.String, TypeToken.alloc("int8"),
				new SqlValueTransformerInteger());

		cs.registerCoercion(TypeToken.String, TypeToken.alloc("int4"),
				new SqlValueTransformerInteger());

		cs.registerCoercion(TypeToken.String, TypeToken.alloc("int"),
				new SqlValueTransformerInteger());

		
		cs.registerCoercion(TypeToken.Int, TypeToken.Float,
				new SqlValueTransformerFloat());
		
		
		// TODO Finally clean up the TypeSystem 
		// The coercion system is still a hack...
		cs.registerCoercion(TypeToken.String, TypeToken.alloc("INTEGER"),
				new SqlValueTransformerInteger());

		
//		cs.registerCoercion(TypeToken.Date, TypeToken.,
//				new SqlValueTransformer());

		// FunctionRegistry functionRegistry = new FunctionRegistry();

		ExprBindingSubstitutor exprBindingSubstitutor = new ExprBindingSubstitutorImpl();

		// Eliminates rdf terms from Expr (this is datatype independent)
		ExprEvaluator exprEvaluator = SqlTranslationUtils
				.createDefaultEvaluator();

		// Computes types for Expr, thereby yielding SqlExpr
		TypedExprTransformer typedExprTransformer = new TypedExprTransformerImpl(
				typeSystem);

		// Obtain DBMS specific string representation for SqlExpr

		DatatypeToStringPostgres typeSerializer = new DatatypeToStringPostgres();

		SqlLiteralMapper sqlLiteralMapper = new SqlLiteralMapperDefault(
				typeSerializer);
		SqlExprSerializerSystem serializerSystem = new SqlExprSerializerSystemImpl(
				typeSerializer, sqlLiteralMapper);

		// ExprEvaluator exprEvaluator = new
		// ExprEvaluatorPartial(functionRegistry, typedExprTransformer)

		// {
		// Method m = DefaultCoercions.class.getMethod("toDouble",
		// Integer.class);
		// XMethod x = XMethodImpl.createFromMethod("toDouble", ds, null, m);
		// ds.registerCoercion(x);
		// }
		//
		// /*
		// * Methods that can only be rewritten
		// */
		//
		//
		// // For most of the following functions, we can rely on Jena for their
		// // evaluation
		// ExprEvaluator evaluator = new ExprEvaluatorJena();
		//
		//

		FunctionModel<TypeToken> sqlModel = typeSystem.getSqlFunctionModel();
		Multimap<String, String> sparqlSqlDecls = typeSystem.getSparqlSqlDecls();
		Map<String, SqlExprEvaluator> sqlImpls = typeSystem.getSqlImpls();

		
		registerSqlOperatorBatchCompare(sqlModel, "lessThan");
		registerSqlOperatorBatchCompare(sqlModel, "lessThanOrEqual");
		registerSqlOperatorBatchCompare(sqlModel, "equal");		
		registerSqlOperatorBatchCompare(sqlModel, "greaterThan");
		registerSqlOperatorBatchCompare(sqlModel, "greaterThanOrEqual");
		
		registerSqlOperatorBatchNumeric(sqlModel, "numericPlus");
		registerSqlOperatorBatchNumeric(sqlModel, "numericMinus");
		registerSqlOperatorBatchNumeric(sqlModel, "numericMultiply");
		registerSqlOperatorBatchNumeric(sqlModel, "numericDivide");

		sqlModel.registerFunction("str@str", "str", MethodSignature.create(false, TypeToken.String, TypeToken.String));
		sqlModel.registerFunction("str@double", "str", MethodSignature.create(false, TypeToken.String, TypeToken.Double));
		sqlModel.registerFunction("str@float", "str", MethodSignature.create(false, TypeToken.String, TypeToken.Float));
		sqlModel.registerFunction("str@int", "str", MethodSignature.create(false, TypeToken.String, TypeToken.Int));

		sqlModel.registerFunction("double@str", "double", MethodSignature.create(false, TypeToken.Double, TypeToken.String));		

		sqlModel.registerFunction("isNotNull@object", "isNotNull", MethodSignature.create(false, TypeToken.Boolean, TypeToken.Object));

		
		sparqlSqlDecls.putAll("<", sqlModel.getIdsByName("lessThan"));
		sparqlSqlDecls.putAll("<=", sqlModel.getIdsByName("lessThanOrEqual"));
		sparqlSqlDecls.putAll("=", sqlModel.getIdsByName("equal"));
		sparqlSqlDecls.putAll(">", sqlModel.getIdsByName("greaterThan"));
		sparqlSqlDecls.putAll(">=", sqlModel.getIdsByName("greaterThanOrEqual"));

		sparqlSqlDecls.putAll("+", sqlModel.getIdsByName("numericPlus"));
		sparqlSqlDecls.putAll("-", sqlModel.getIdsByName("numericMinus"));
		sparqlSqlDecls.putAll("/", sqlModel.getIdsByName("numericMultiply"));
		sparqlSqlDecls.putAll("*", sqlModel.getIdsByName("numericDivide"));
		
		sparqlSqlDecls.put("str", "str@str");
		sparqlSqlDecls.put("str", "str@double");
		sparqlSqlDecls.put("str", "str@float");
		sparqlSqlDecls.put("str", "str@int");
		sparqlSqlDecls.put(XSD.xdouble.getURI(), "double@str");
		
		sparqlSqlDecls.put("bound", "isNotNull@object");

		putForAll(sqlImpls, sqlModel.getIdsByName("lessThan"), new SqlExprEvaluator_Compare(typeSystem, SqlExprFactoryUtils.factoryLessThan));
		putForAll(sqlImpls, sqlModel.getIdsByName("lessThanOrEqual"), new SqlExprEvaluator_Compare(typeSystem, SqlExprFactoryUtils.factoryLessThanOrEqual));
		putForAll(sqlImpls, sqlModel.getIdsByName("equal"), new SqlExprEvaluator_Compare(typeSystem, SqlExprFactoryUtils.factoryEqual));
		putForAll(sqlImpls, sqlModel.getIdsByName("greaterThan"), new SqlExprEvaluator_Compare(typeSystem, SqlExprFactoryUtils.factoryGreaterThan));
		putForAll(sqlImpls, sqlModel.getIdsByName("greaterThanOrEqual"), new SqlExprEvaluator_Compare(typeSystem, SqlExprFactoryUtils.factoryGreaterThanOrEqual));

		putForAll(sqlImpls, sqlModel.getIdsByName("numericPlus"), new SqlExprEvaluator_Arithmetic());

		putForAll(sqlImpls, sqlModel.getIdsByName("+"), new SqlExprEvaluator_Compare(typeSystem, SqlExprFactoryUtils.factoryNumericPlus));
		putForAll(sqlImpls, sqlModel.getIdsByName("-"), new SqlExprEvaluator_Compare(typeSystem, SqlExprFactoryUtils.factoryNumericMinus));
		putForAll(sqlImpls, sqlModel.getIdsByName("*"), new SqlExprEvaluator_Compare(typeSystem, SqlExprFactoryUtils.factoryNumericMultiply));
//		putForAll(sqlImpls, sqlModel.getIdsByName("/"), new SqlExprEvaluator_Compare(typeSystem, SqlExprFactoryUtils.factoryNumericDivide));

		

		sqlModel.registerFunction("logicalAnd@boolean", "logicalAnd", MethodSignature.create(false, TypeToken.Boolean, TypeToken.Boolean, TypeToken.Boolean));
		sparqlSqlDecls.putAll("&&", sqlModel.getIdsByName("logicalAnd"));
		putForAll(sqlImpls, sqlModel.getIdsByName("logicalAnd"), new SqlExprEvaluator_LogicalAnd());

		sqlModel.registerFunction("logicalOr@boolean", "logicalOr", MethodSignature.create(false, TypeToken.Boolean, TypeToken.Boolean, TypeToken.Boolean));
		sparqlSqlDecls.putAll("||", sqlModel.getIdsByName("logicalOr"));
		putForAll(sqlImpls, sqlModel.getIdsByName("logicalOr"), new SqlExprEvaluator_LogicalOr());

		sqlModel.registerFunction("logicalNot@boolean", "logicalNot", MethodSignature.create(false, TypeToken.Boolean, TypeToken.Boolean));
		sparqlSqlDecls.putAll("!", sqlModel.getIdsByName("logicalNot"));
		putForAll(sqlImpls, sqlModel.getIdsByName("logicalNot"), new SqlExprEvaluator_LogicalNot());


		sqlModel.registerFunction("concat@str", "concat", MethodSignature.create(true, TypeToken.String, TypeToken.String));		
		sparqlSqlDecls.put("concat", "concat@str");

		
		// register a parse int function
		sqlModel.registerFunction("parseInt@str", "parseInt", MethodSignature.create(false, TypeToken.Int, TypeToken.String));		
		//sparqlSqlDecls.put("concat", "concat@object");
		
		FunctionModelMeta sqlMetaModel = typeSystem.getSqlFunctionMetaModel();
		
		sqlMetaModel.getInverses().put("str@int", "parseInt@str");
		
		sqlMetaModel.getComparators().addAll(sqlModel.getIdsByName("lessThan"));
		sqlMetaModel.getComparators().addAll(sqlModel.getIdsByName("lessThanOrEqual"));
		sqlMetaModel.getComparators().addAll(sqlModel.getIdsByName("equal"));
		sqlMetaModel.getComparators().addAll(sqlModel.getIdsByName("greaterThanOrEqual"));
		sqlMetaModel.getComparators().addAll(sqlModel.getIdsByName("greaterThan"));
		
		
		//sqlModel.getInverses().put();
		sqlImpls.put("parseInt@str", new SqlExprEvaluator_ParseInt());
		

		
		

		// Geographic
		TypeToken typeGeometry = TypeToken.alloc("geometry");
		String bif = "http://www.openlinksw.com/schemas/bif#";

		
		sqlModel.registerFunction("geometry ST_GeomFromPoint(float, float)", "ST_GeomFromPoint", MethodSignature.create(false, typeGeometry, TypeToken.Float, TypeToken.Float));
		sparqlSqlDecls.putAll(bif + "st_point", sqlModel.getIdsByName("ST_GeomFromPoint"));
		sqlImpls.put("geometry ST_GeomFromPoint(float, float)", new SqlExprEvaluator_PassThrough(typeGeometry, "ST_GeomFromPoint"));

		sqlModel.registerFunction("boolean ST_Intersects(geometry, geometry, float)", "ST_Intersects", MethodSignature.create(false, TypeToken.Boolean, typeGeometry, typeGeometry, TypeToken.Float));
		sparqlSqlDecls.putAll(bif + "st_intersects", sqlModel.getIdsByName("ST_Intersects"));
		sqlImpls.put("boolean ST_Intersects(geometry, geometry, float)", new SqlExprEvaluator_PassThrough(TypeToken.Boolean, "ST_Intersects"));

				
		
		sqlModel.registerCoercion("float toFloat(int)", "toFloat", MethodSignature.create(false, TypeToken.Float, TypeToken.Int));
		//sqlImpls.put("float toFloat(int)", new SqlE);
		
		
		
		// tag the comparators as comparators...
		
		
		// urlEncode
		{
			MethodSignature<TypeToken> sig = MethodSignature.create(false, TypeToken.String, TypeToken.String);

			SparqlFunction f = new SparqlFunctionImpl(SparqlifyConstants.urlEncode, sig, null, null);
			typeSystem.registerSparqlFunction(f);
		}

		
		

		

		
		
		//
		
		// {
		// MethodSignature<String> sig = MethodSignature.create(false,
		// SparqlifyConstants.numericTypeLabel,
		// SparqlifyConstants.numericTypeLabel,
		// SparqlifyConstants.numericTypeLabel);
		// SqlExprEvaluator evaluator = new SqlExprEvaluator_LogicalOr();
		//
		// SparqlFunction f = new SparqlFunctionImpl("or", sig, evaluator,
		// null);
		// typeSystem.registerSparqlFunction(f);
		// SqlFunctionSerializer serializer = new
		// SqlFunctionSerializerOp2("OR");
		// serializerSystem.addSerializer("or", serializer);
		//
		// }

		{
			// MethodSignature<String> sig = MethodSignature.create(false,
			// SparqlifyConstants.numericTypeLabel,
			// SparqlifyConstants.numericTypeLabel,
			// SparqlifyConstants.numericTypeLabel);
			// SqlExprEvaluator evaluator = new
			// SqlExprEvaluator_Arithmetic(typeSystem);
			//
			// XSDFuncOp.add(nv1, nv2);
			// XSDFuncOp.classifyNumeric(fName, nv);
			//
			// // As a fallback where Jena can't evaluate it, register a
			// transformation to an SQL expression.
			// SparqlFunction f = new SparqlFunctionImpl("+", sig, evaluator,
			// null);
			// typeSystem.registerSparqlFunction(f);
			// SqlFunctionSerializer serializer = new
			// SqlFunctionSerializerOp2("+");
			// serializerSystem.addSerializer("+", serializer);
		}

	}
	
	
	public static void testExprRewrite(TypeSystem typeSystem)
	{
		
		ExprBindingSubstitutor exprBindingSubstitutor = new ExprBindingSubstitutorImpl();

		// Eliminates rdf terms from Expr (this is datatype independent)
		ExprEvaluator exprEvaluator = SqlTranslationUtils
				.createDefaultEvaluator();

		// Computes types for Expr, thereby yielding SqlExpr
		TypedExprTransformer typedExprTransformer = new TypedExprTransformerImpl(
				typeSystem);

		// Obtain DBMS specific string representation for SqlExpr

		DatatypeToStringPostgres typeSerializer = new DatatypeToStringPostgres();

		SqlLiteralMapper sqlLiteralMapper = new SqlLiteralMapperDefault(
				typeSerializer);
		SqlExprSerializerSystem serializerSystem = new SqlExprSerializerSystemImpl(
				typeSerializer, sqlLiteralMapper);


		// SqlTranslationUtils;

		// Expr e0 = ExprUtils.parse("?a + ?b = ?a");
		// Expr e0 =
		// ExprUtils.parse("<http://aksw.org/sparqlify/typedLiteral>(?c + ?d, 'http://www.w3.org/2001/XMLSchema#double')");
		// Expr e0 = ExprUtils.parse("?a = ?b || ?b = ?a && !(?a = ?a)");
		//Expr e0 = ExprUtils.parse("?e = '1'");

		//Expr e0 = ExprUtils.parse("?c < ?d + 1");
		//Expr e0 = ExprUtils.parse("(?e + 1) * (?e + 4)");
		Expr e0 = ExprUtils.parse("?f = <http://foobar>");
		
		Expr a = ExprUtils.parse("<http://aksw.org/sparqlify/uri>(?website)");
		// Expr b =
		// ExprUtils.parse("<http://aksw.org/sparqlify/plainLiteral>(<http://aksw.org/sparqlify/plainLiteral>(?foo))");
		Expr b = ExprUtils
				.parse("<http://aksw.org/sparqlify/uri>(<http://aksw.org/sparqlify/plainLiteral>(?foo))");

		Expr c = ExprUtils
				.parse("<http://aksw.org/sparqlify/typedLiteral>(1, 'http://www.w3.org/2001/XMLSchema#int')");
		Expr d = ExprUtils
				.parse("<http://aksw.org/sparqlify/typedLiteral>(2, 'http://www.w3.org/2001/XMLSchema#int')");

		Expr e = ExprUtils
				.parse("<http://aksw.org/sparqlify/typedLiteral>(?x, 'http://www.w3.org/2001/XMLSchema#int')");

		Expr f = ExprUtils
				.parse("<http://aksw.org/sparqlify/uri>('http://foobar')");
		// Expr e = ExprUtils.parse("");


		
		Map<Var, Expr> binding = new HashMap<Var, Expr>();
		binding.put(Var.alloc("a"), a);
		binding.put(Var.alloc("b"), b);
		binding.put(Var.alloc("c"), c);
		binding.put(Var.alloc("d"), d);
		binding.put(Var.alloc("e"), e);
		binding.put(Var.alloc("f"), f);

		// ExprHolder aaa;

		Map<String, TypeToken> typeMap = new HashMap<String, TypeToken>();
		typeMap.put("website", TypeToken.String);
		typeMap.put("foo", TypeToken.String);
		typeMap.put("x", TypeToken.Int);
		// typeMap.put("foo", TypeToken.Double);
		// typeMap.put("foo", TypeToken.Double);

		logger.debug("[ExprRewrite Phase 0]: " + e0);

		Expr e1 = exprBindingSubstitutor.substitute(e0, binding);
		logger.debug("[ExprRewrite Phase 1]: " + e1);

		//Expr e2 = exprTransformer.transform(e1);
		//logger.debug("[ExprRewrite Phase 2]: " + e2);

		RdfTermEliminatorImpl exprTrns = SqlTranslationUtils.createDefaultTransformer();
		E_RdfTerm e2 = exprTrns._transform(e1);
		logger.debug("[ExprRewrite Phase 2]: " + e2);
		
		// This step should must be generalized to return an SqlExprRewrite
		Expr e3 = exprEvaluator.transform(e2);
		logger.debug("[ExprRewrite Phase 3]: " + e3);

		ExprSqlRewrite e4 = typedExprTransformer.rewrite(e3, typeMap);
		logger.debug("[ExprRewrite Phase 4]: " + e4);

		Expr et = e4.getExpr();
		if (et instanceof ExprSqlBridge) {

			ExprSqlBridge bridge = (ExprSqlBridge) et;

			SqlExpr ex = bridge.getSqlExpr();

			String e5 = serializerSystem.serialize(ex);
			logger.debug("[ExprRewrite Phase 5]: " + e5);
		} else {

			logger.debug("Done rewriting: ");
			logger.debug("" + et);
			logger.debug("" + e4.getProjection());

		}

		// How can we express,
		// that + only take arguments of type TypedLiteral? (possibly even
		// "TypedLiteral WHERE datatype is subClassOf Numeric")
		// Btw, I'd like to avoid using a reasoner in the rewriting process,
		// although we could model it with owl I guess

		// The Sparql function '='(?a, ?b) has the substitution
		// typedLiteral(sql:equals(?a, ?b), xsd:boolean) defined.
		// For sql:equals an appropriate serializer is defined.
		//

		//
		//
		//
		// {
		// MethodSignature<TypeToken> signature =
		// MethodSignature.create(TypeToken.Boolean,
		// Arrays.asList(TypeTokenPostgis.Geometry, TypeTokenPostgis.Geometry),
		// null);
		//
		// XMethod x = XMethodImpl.create(ds, "ST_INTERSECTS", signature);
		// ds.registerSqlFunction("http://ex.org/fn/intersects", x);
		// }
		//
		// {
		// SqlExprEvaluator evaluator = new SqlExprEvaluator_Concat();
		// ds.createSparqlFunction("concat", evaluator);
		//
		// /*
		// MethodSignature<TypeToken> signature = MethodSignature.create(true,
		// TypeToken.String, TypeToken.Object);
		//
		// // TODO: We need a serializer for concat
		// XMethod x = XMethodImpl.create(ds, "||", signature);
		// ds.registerSqlFunction("concat", x);
		// */
		// }
		//
		//
		// {
		// SqlExprEvaluator evaluator = new SqlExprEvaluator_LogicalAnd();
		// ds.createSparqlFunction("&&", evaluator);
		// }
		//
		// {
		// SqlExprEvaluator evaluator = new SqlExprEvaluator_LogicalOr();
		// ds.createSparqlFunction("||", evaluator);
		// }
		//
		// {
		// SqlExprEvaluator evaluator = new SqlExprEvaluator_LogicalNot();
		// ds.createSparqlFunction("!", evaluator);
		// }
		//
		//
		// /*
		// {
		// SqlExprEvaluator evaluator = new SqlExprEvaluator_Equals(ds);
		// ds.createSparqlFunction("=", evaluator);
		// }
		// */
		//
		//
		// {
		// String[] compareSymbols = new String[]{"<=", "<", "=", ">", ">="};
		// for(String opSymbol : compareSymbols) {
		// ds.createSparqlFunction(opSymbol, new
		// SqlExprEvaluator_Compare(opSymbol, ds));
		// }
		// }

	}

	public static void main(String[] args) throws IOException {
		RdfViewSystemOld.initSparqlifyFunctions();

		TypeSystem typeSystem = createDefaultDatatypeSystem();

		System.out.println("Yay so far");

		// SqlTranslator sqlTranslator = new SqlTranslatorImpl(typeSystem);
		// ExprEvaluator exprTransformer =
		// SqlTranslationUtils.createDefaultEvaluator();

	}
}
