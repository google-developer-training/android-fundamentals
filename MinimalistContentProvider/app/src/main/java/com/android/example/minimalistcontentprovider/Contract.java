package com.android.example.minimalistcontentprovider;
/*
 * Copyright (C) 2015 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.net.Uri;

/**
 * Created by vhaecky on 5/27/16.
 */

public final class Contract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty private constructor. This is a standard pattern.
    private Contract() {}

    /**
     * The content URI needs the following structure:
     *     * scheme (or content URIs, this is always content://)
     *     * authority (this represents the domain)
     *     * path (this represents the path to the page)
     *     * ID (this is the name of the file/table, unique within the namespace)
     *
     * Accessing the FIRST word in the list requires this URI for this app sample.
     * content://com.android.example.minimalistcontentprovider.provider/words/0
     *
     * This URI will return all the words:
     * content://com.android.example.minimalistcontentprovider.provider/words
     *
     * Designing a good URI scheme is a topic in itself and not covered in this practical.
     */

    // Customarily, to make Authority unique, it's the package name extended with "provider".
    // Must match with the authority defined in Android Manifest.
    public static final String AUTHORITY = "com.android.example.minimalistcontentprovider.provider";

    // The content path is an abstract semantic identifier of the data you are interested in.
    // It does not predict or presume in what form the data is stored or organized in the
    // background. As such, "words" could resolve into the name of a table, the name of a file,
    // or in this example, the name of a list.
    public static final String CONTENT_PATH = "words";


    // A content:// style URI to the authority for this table */
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + CONTENT_PATH);

    static final int ALL_ITEMS = -2;
    static final String WORD_ID = "id";

    // MIME types for this content provider.
    // https://developer.android.com/guide/topics/providers/content-provider-creating.html#MIMETypes
    static final String SINGLE_RECORD_MIME_TYPE = "vnd.android.cursor.item/vnd.com.example.provider.words";
    static final String MULTIPLE_RECORDS_MIME_TYPE = "vnd.android.cursor.dir/vnd.com.example.provider.words";

}
