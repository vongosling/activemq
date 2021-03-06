/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.activemq.broker.view;

import java.util.HashMap;
import java.util.Map;

import org.apache.activemq.broker.BrokerRegistry;
import org.apache.activemq.broker.BrokerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageBrokerViewRegistry {

    private static final Logger LOG = LoggerFactory.getLogger(BrokerRegistry.class);
    private static final MessageBrokerViewRegistry INSTANCE = new MessageBrokerViewRegistry();

    private final Object mutex = new Object();
    private final Map<String, MessageBrokerView> brokerViews = new HashMap<String, MessageBrokerView>();

    public static MessageBrokerViewRegistry getInstance() {
        return INSTANCE;
    }

    /**
     * @param brokerName
     * @return the BrokerService
     */
    public MessageBrokerView lookup(String brokerName) {
        MessageBrokerView result = null;
        synchronized (mutex) {
            result = brokerViews.get(brokerName);
            if (result==null){
                BrokerService brokerService = BrokerRegistry.getInstance().lookup(brokerName);
                if (brokerService != null){
                    result = new MessageBrokerView(brokerService);
                    brokerViews.put(brokerName,result);
                }
            }

        }
        return result;
    }
}
