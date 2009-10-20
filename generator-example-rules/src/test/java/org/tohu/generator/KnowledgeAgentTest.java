package org.tohu.generator;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.agent.KnowledgeAgent;
import org.drools.agent.KnowledgeAgentFactory;
import org.drools.builder.DecisionTableConfiguration;
import org.drools.builder.DecisionTableInputType;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.logger.KnowledgeRuntimeLogger;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.QueryResults;
import org.drools.runtime.rule.QueryResultsRow;
import org.junit.Test;
import org.tohu.Group;
import org.tohu.InvalidAnswer;

/**
 * Simple Decision Table Test
 */
public class KnowledgeAgentTest {

	@SuppressWarnings("deprecation")
	@Test
	public void testControlDrl() throws Exception {
		
		String url = "file:/Users/rah/projects/generator-example/generator-example-rules/src/test/resources/test.xml";
		
		try {
			KnowledgeAgent kagent = KnowledgeAgentFactory.newKnowledgeAgent( "MyAgent" );
			kagent.applyChangeSet( ResourceFactory.newUrlResource( url ) );

			KnowledgeBase kbase = kagent.getKnowledgeBase();
			StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
			KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory.newFileLogger(ksession, "test");

			// fire rules to generate new rules and add to kbase
			ksession.fireAllRules();
			
			QueryResults results = ksession.getQueryResults( "get groups" );
			for ( QueryResultsRow row : results ) {
			    Group group = ( Group ) row.get( "$group" );
			    System.out.println("Group : " + group.getLabel());
			}		
			
			logger.close();
		} catch (Throwable t) {
			t.printStackTrace();
		}		
	}
	

	private static KnowledgeBase readKnowledgeBase() throws Exception {
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		DecisionTableConfiguration config = KnowledgeBuilderFactory.newDecisionTableConfiguration();
		config.setInputType(DecisionTableInputType.XLS);
		kbuilder.add(ResourceFactory.newClassPathResource("org/tohu/generator/TohuDtableLoader.xls"), ResourceType.DTABLE, config);
		kbuilder.add(ResourceFactory.newClassPathResource("org/tohu/generator/TohuBuilder.drl"), ResourceType.DRL);
		KnowledgeBuilderErrors errors = kbuilder.getErrors();
		if (errors.size() > 0) {
			for (KnowledgeBuilderError error: errors) {
				System.err.println(error);
			}
			throw new IllegalArgumentException("Could not parse knowledge.");
		}
		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
		return kbase;
	}

}