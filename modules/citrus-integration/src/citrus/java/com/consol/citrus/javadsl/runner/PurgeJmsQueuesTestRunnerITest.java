/*
 * Copyright 2006-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.consol.citrus.javadsl.runner;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.definition.PurgeJmsQueueActionDefinition;
import com.consol.citrus.dsl.runner.TestActionConfigurer;
import com.consol.citrus.dsl.testng.TestNGCitrusTestRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;

/**
 * @author Christoph Deppisch
 */
@Test
public class PurgeJmsQueuesTestRunnerITest extends TestNGCitrusTestRunner {
    
    @Autowired
    @Qualifier("connectionFactory")
    private ConnectionFactory connectionFactory;
    
    @Autowired
    @Qualifier("testQueue1")
    private Queue queue1;
    
    @Autowired
    @Qualifier("testQueue2")
    private Queue queue2;
    
    @Autowired
    @Qualifier("testQueue3")
    private Queue queue3;
    
    @CitrusTest
    public void PurgeJmsQueuesTestRunnerITest() {
        purgeQueues(new TestActionConfigurer<PurgeJmsQueueActionDefinition>() {
            @Override
            public void configure(PurgeJmsQueueActionDefinition definition) {
                definition.queue("Citrus.Queue.Dummy")
                        .queue("Citrus.Queue.Dummy.One.In")
                        .queue("Citrus.Queue.Dummy.One.Out")
                        .queue("Citrus.Queue.Dummy.One.In")
                        .queue("Citrus.Queue.Dummy.One.Out")
                        .queue("Citrus.Queue.Dummy.Three.In")
                        .queue("Citrus.Queue.Dummy.Three.Out");
            }
        });
        
        purgeQueues(new TestActionConfigurer<PurgeJmsQueueActionDefinition>() {
            @Override
            public void configure(PurgeJmsQueueActionDefinition definition) {
                definition.connectionFactory(connectionFactory)
                        .timeout(150L)
                        .queue("Citrus.Queue.Dummy")
                        .queue("Citrus.Queue.Dummy.One.In")
                        .queue("Citrus.Queue.Dummy.One.Out")
                        .queue("Citrus.Queue.Dummy.One.In")
                        .queue("Citrus.Queue.Dummy.One.Out")
                        .queue("Citrus.Queue.Dummy.Three.In")
                        .queue("Citrus.Queue.Dummy.Three.Out");
            }
        });
        
        purgeQueues(new TestActionConfigurer<PurgeJmsQueueActionDefinition>() {
            @Override
            public void configure(PurgeJmsQueueActionDefinition definition) {
                definition.queue(queue1)
                        .queue(queue2)
                        .queue(queue3)
                        .queue("Citrus.Queue.Dummy.One.In")
                        .queue("Citrus.Queue.Dummy.One.Out")
                        .queue("Citrus.Queue.Dummy.Three.In")
                        .queue("Citrus.Queue.Dummy.Three.Out");
            }
        });
    }
}