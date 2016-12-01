WordListLoader - Solution Code
==============================

Client app for the word list content provider that
uses a CursorLoader to load the word list in the background.


Introduction
------------

This is the solution code for Practical 13.1, "Load and display data fetched
from a content provider".

In this practical you will learn how to load data provided by another app's
content provider in the background and display it to the user, when it is ready.
Querying a ContentProvider for data you want to display takes time.
If you run the query directly from an activity, it may get blocked and cause
the system to issue an "Application Not Responding" message.
Even if it doesn't, users will see an annoying delay in the UI. To avoid these
problems, you should initiate a query on a separate thread, wait for it to
finish, and then display the results.
You can do this in a straightforward way by using an object that runs a query
asynchronously in the background and reconnects to your activity when it's
finished. You do this with a loader, specifically, a CursorLoader.
Besides doing the initial background query, a CursorLoader automatically
re-runs the query when data associated with the query changes.


Pre-requisites
--------------

For this sample you should be familiar with:
- Displaying data in a RecyclerView
- Adapters
- Cursors (see previous practical and concepts)
- AsyncTaskLoader
- Content Providers


Getting Started
---------------

1. Download and run this sample.

License
-------

Copyright 2016 Google, Inc.

Licensed to the Apache Software Foundation (ASF) under one or more contributor
license agreements.  See the NOTICE file distributed with this work for
additional information regarding copyright ownership.  The ASF licenses this
file to you under the Apache License, Version 2.0 (the "License"); you may not
use this file except in compliance with the License.  You may obtain a copy of
the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
License for the specific language governing permissions and limitations under
the License.
