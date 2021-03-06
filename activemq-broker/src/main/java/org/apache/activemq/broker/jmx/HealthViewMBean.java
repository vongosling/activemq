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
package org.apache.activemq.broker.jmx;

import javax.management.openmbean.TabularData;
import java.util.List;

/**
 * Returns the status events of the broker to indicate any warnings.
 */
public interface HealthViewMBean {

    public TabularData health() throws Exception;

    /**
     * Warning this method can only be invoked if you have the correct version
     * of {@link HealthStatus} on your classpath or you use something
     * like <a href="http://jolokia.org/">jolokia</a> to access JMX.
     *
     * If in doubt, please use the {@link #status()} method instead!
     */
    @MBeanInfo("List of warnings and errors about the current health of the Broker - empty list is Good!")
    List<HealthStatus> healthList() throws Exception;

    /**
     * @return  String representation of the current Broker state
     */
    @MBeanInfo("String representation of current Broker state")
    String getCurrentStatus();
}
