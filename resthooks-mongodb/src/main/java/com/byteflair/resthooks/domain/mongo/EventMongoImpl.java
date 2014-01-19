/*******************************************************************************
 * Copyright (c), 2014, Byteflair S.L. Code contributed by Daniel Cerecedo.
 * This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License version 3 as published by the Free Software Foundation.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GPLv3 for more details.
 ******************************************************************************/

package com.byteflair.resthooks.domain.mongo;

import com.byteflair.resthooks.domain.EventImpl;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by dcerecedo on 1/19/14.
 */
@Document(collection="events")
public class EventMongoImpl extends EventImpl {
}
