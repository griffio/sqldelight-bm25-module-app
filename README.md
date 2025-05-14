# SqlDelight 2.1.x Postgresql VectorChord bm25 module support prototype 

https://github.com/cashapp/sqldelight

**Experimental**

VectorChord-BM25, a new extension for PostgreSQL’s full-text search

https://github.com/tensorchord/VectorChord-bm25

Use with SqlDelight 2.1.0-SNAPSHOT

---

Instead of a new dialect or adding PostgreSql extensions into the core PostgreSql grammar e.g. 

Use a custom SqlDelight module to implement grammar and type resolvers for VectorChord bm25 operations

```sql
SET search_path TO bm25_catalog;

CREATE EXTENSION IF NOT EXISTS vchord_bm25;

CREATE TABLE Documents (
    id BIGINT GENERATED ALWAYS AS IDENTITY,
    passage TEXT,
    embedding bm25vector
);

CREATE INDEX documents_embedding_bm25 ON Documents USING bm25 (embedding bm25_ops);
```

To calculate the BM25 score (real type) between a bm25vector and a query, you’ll first need a document set. 
Once that’s in place, you can use the <&> operator to perform the calculation.

```sql
rankDocuments:
SELECT id, passage, embedding <&> to_bm25query('documents_embedding_bm25', :document, 'Bert') AS rank
FROM Documents
ORDER BY rank
LIMIT 10;
```

Use Docker container to run image with pre-installed extension

```shell
docker run \
  --name vectorchord-demo \
  -e POSTGRES_PASSWORD=mysecretpassword \
  -p 5432:5432 \
  -d ghcr.io/tensorchord/vchord_bm25-postgres:pg17-v0.1.0
```
