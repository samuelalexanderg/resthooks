/*******************************************************************************
 * Copyright (c), 2014, Byteflair S.L. Code contributed by Daniel Cerecedo.
 * This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License version 3 as published by the Free Software Foundation.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GPLv3 for more details.
 ******************************************************************************/

package com.byteflair.resthooks.db.mongo;

import com.byteflair.resthooks.api.Subscription;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by dcerecedo on 1/19/14.
 */
public interface SubscriptionMongoRepository extends CrudRepository<Subscription, String> {
}
