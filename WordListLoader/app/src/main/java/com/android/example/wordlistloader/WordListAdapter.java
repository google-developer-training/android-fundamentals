/*
 * Copyright (C) 2016 Google Inc.
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

package com.android.example.wordlistloader;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static com.android.example.wordlistloader.R.id.word;

/**
 * Simple Adapter for a RecyclerView with click handler for each item in the ViewHolder.
 */
public class WordListAdapter
        extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    class WordViewHolder extends RecyclerView.ViewHolder {
        public final TextView wordItemView;

        public WordViewHolder(View itemView) {
            super(itemView);
            wordItemView = (TextView) itemView.findViewById(word);
        }
    }

    private static final String TAG = WordListAdapter.class.getSimpleName();

    private Cursor mCursor = null;

    private final LayoutInflater mInflater;
    private Context mContext;

    public WordListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    public void setData(Cursor cursor) {
        mCursor = cursor;
        notifyDataSetChanged();
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.wordlist_item, parent, false);
        return new WordViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {

        String word = "";

        if (mCursor != null) {
            if (mCursor.moveToPosition(position)) {
                int indexWord = mCursor.getColumnIndex(Contract.WordList.KEY_WORD);
                word = mCursor.getString(indexWord);
                holder.wordItemView.setText(word);
            } else {
                holder.wordItemView.setText(R.string.error_no_word);
            }
        } else {
            Log.e (TAG, "onBindViewHolder: Cursor is null.");
        }
    }

    @Override
    public int getItemCount() {
        if (mCursor != null) {
            return mCursor.getCount();
        } else {
            return -1;
        }
    }
}
