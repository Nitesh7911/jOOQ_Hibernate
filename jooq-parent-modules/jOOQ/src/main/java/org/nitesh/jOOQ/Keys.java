/*
 * This file is generated by jOOQ.
 */
package org.nitesh.jOOQ;


import org.nitesh.jOOQ.tables.Author;
import org.nitesh.jOOQ.tables.AuthorBook;
import org.nitesh.jOOQ.tables.Book;
import org.nitesh.jOOQ.tables.records.AuthorBookRecord;
import org.nitesh.jOOQ.tables.records.AuthorRecord;
import org.nitesh.jOOQ.tables.records.BookRecord;

import org.jooq.ForeignKey;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables in 
 * the default schema.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<AuthorRecord> CONSTRAINT_7 = Internal.createUniqueKey(Author.AUTHOR, DSL.name("CONSTRAINT_7"), new TableField[] { Author.AUTHOR.ID }, true);
    public static final UniqueKey<AuthorBookRecord> CONSTRAINT_A = Internal.createUniqueKey(AuthorBook.AUTHOR_BOOK, DSL.name("CONSTRAINT_A"), new TableField[] { AuthorBook.AUTHOR_BOOK.AUTHOR_ID, AuthorBook.AUTHOR_BOOK.BOOK_ID }, true);
    public static final UniqueKey<BookRecord> CONSTRAINT_1 = Internal.createUniqueKey(Book.BOOK, DSL.name("CONSTRAINT_1"), new TableField[] { Book.BOOK.ID }, true);

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<AuthorBookRecord, BookRecord> FK17OYV7SHXUDGEXWJA84TE05LJ = Internal.createForeignKey(AuthorBook.AUTHOR_BOOK, DSL.name("FK17OYV7SHXUDGEXWJA84TE05LJ"), new TableField[] { AuthorBook.AUTHOR_BOOK.BOOK_ID }, Keys.CONSTRAINT_1, new TableField[] { Book.BOOK.ID }, true);
    public static final ForeignKey<AuthorBookRecord, AuthorRecord> FK1E3VXF7VDDYB1V4N774TIDDQ0 = Internal.createForeignKey(AuthorBook.AUTHOR_BOOK, DSL.name("FK1E3VXF7VDDYB1V4N774TIDDQ0"), new TableField[] { AuthorBook.AUTHOR_BOOK.AUTHOR_ID }, Keys.CONSTRAINT_7, new TableField[] { Author.AUTHOR.ID }, true);
}
