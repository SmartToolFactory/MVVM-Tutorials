package com.example.android.persistence.db.dao;

import android.arch.lifecycle.ComputableLiveData;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.InvalidationTracker.Observer;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.database.Cursor;
import android.support.annotation.NonNull;
import com.example.android.persistence.db.converter.DateConverter;
import com.example.android.persistence.db.entity.CommentEntity;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@SuppressWarnings("unchecked")
public class CommentDao_Impl implements CommentDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfCommentEntity;

  public CommentDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCommentEntity = new EntityInsertionAdapter<CommentEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `comments`(`id`,`productId`,`text`,`postedAt`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, CommentEntity value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getProductId());
        if (value.getText() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getText());
        }
        final Long _tmp;
        _tmp = DateConverter.toTimestamp(value.getPostedAt());
        if (_tmp == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindLong(4, _tmp);
        }
      }
    };
  }

  @Override
  public void insertAll(List<CommentEntity> products) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfCommentEntity.insert(products);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<CommentEntity>> loadComments(int productId) {
    final String _sql = "SELECT * FROM comments where productId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, productId);
    return new ComputableLiveData<List<CommentEntity>>() {
      private Observer _observer;

      @Override
      protected List<CommentEntity> compute() {
        if (_observer == null) {
          _observer = new Observer("comments") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
          final int _cursorIndexOfProductId = _cursor.getColumnIndexOrThrow("productId");
          final int _cursorIndexOfText = _cursor.getColumnIndexOrThrow("text");
          final int _cursorIndexOfPostedAt = _cursor.getColumnIndexOrThrow("postedAt");
          final List<CommentEntity> _result = new ArrayList<CommentEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final CommentEntity _item;
            _item = new CommentEntity();
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _item.setId(_tmpId);
            final int _tmpProductId;
            _tmpProductId = _cursor.getInt(_cursorIndexOfProductId);
            _item.setProductId(_tmpProductId);
            final String _tmpText;
            _tmpText = _cursor.getString(_cursorIndexOfText);
            _item.setText(_tmpText);
            final Date _tmpPostedAt;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfPostedAt)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfPostedAt);
            }
            _tmpPostedAt = DateConverter.toDate(_tmp);
            _item.setPostedAt(_tmpPostedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    }.getLiveData();
  }

  @Override
  public List<CommentEntity> loadCommentsSync(int productId) {
    final String _sql = "SELECT * FROM comments where productId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, productId);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfProductId = _cursor.getColumnIndexOrThrow("productId");
      final int _cursorIndexOfText = _cursor.getColumnIndexOrThrow("text");
      final int _cursorIndexOfPostedAt = _cursor.getColumnIndexOrThrow("postedAt");
      final List<CommentEntity> _result = new ArrayList<CommentEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final CommentEntity _item;
        _item = new CommentEntity();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final int _tmpProductId;
        _tmpProductId = _cursor.getInt(_cursorIndexOfProductId);
        _item.setProductId(_tmpProductId);
        final String _tmpText;
        _tmpText = _cursor.getString(_cursorIndexOfText);
        _item.setText(_tmpText);
        final Date _tmpPostedAt;
        final Long _tmp;
        if (_cursor.isNull(_cursorIndexOfPostedAt)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getLong(_cursorIndexOfPostedAt);
        }
        _tmpPostedAt = DateConverter.toDate(_tmp);
        _item.setPostedAt(_tmpPostedAt);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
