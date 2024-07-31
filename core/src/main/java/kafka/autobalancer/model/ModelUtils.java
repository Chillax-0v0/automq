/*
 * Copyright 2024, AutoMQ HK Limited.
 *
 * The use of this file is governed by the Business Source License,
 * as detailed in the file "/LICENSE.S3Stream" included in this repository.
 *
 * As of the Change Date specified in that file, in accordance with
 * the Business Source License, use of this software will be governed
 * by the Apache License, Version 2.0
 */

package kafka.autobalancer.model;

import java.util.Map;

public class ModelUtils {

    public static void moveReplicaLoad(BrokerUpdater.Broker src, BrokerUpdater.Broker dest,
                                       TopicPartitionReplicaUpdater.TopicPartitionReplica replica) {
        for (Map.Entry<Byte, AbstractInstanceUpdater.Load> load : replica.getLoads().entrySet()) {
            byte resource = load.getKey();
            src.reduceLoad(resource, load.getValue());
            dest.addLoad(resource, load.getValue());
        }
    }

}
