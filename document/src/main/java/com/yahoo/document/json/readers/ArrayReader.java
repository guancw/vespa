// Copyright 2017 Yahoo Inc. Licensed under the terms of the Apache 2.0 license. See LICENSE in the project root.
package com.yahoo.document.json.readers;

import com.yahoo.document.DataType;
import com.yahoo.document.datatypes.CollectionFieldValue;
import com.yahoo.document.datatypes.FieldValue;
import com.yahoo.document.json.TokenBuffer;

import java.util.List;

import static com.yahoo.document.json.readers.JsonParserHelpers.expectArrayStart;
import static com.yahoo.document.json.readers.SingleValueReader.readSingleValue;

public class ArrayReader {
    public static void fillArrayUpdate(TokenBuffer buffer, int initNesting, DataType valueType, List<FieldValue> arrayContents) {
        while (buffer.nesting() >= initNesting) {
            arrayContents.add(readSingleValue(buffer, valueType));
            buffer.next();
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static void fillArray(TokenBuffer buffer, CollectionFieldValue parent, DataType valueType) {
        int initNesting = buffer.nesting();
        expectArrayStart(buffer.currentToken());
        buffer.next();
        while (buffer.nesting() >= initNesting) {
            parent.add(readSingleValue(buffer, valueType));
            buffer.next();
        }
    }
}