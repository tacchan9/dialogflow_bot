package jp.co.chat.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2018-02-21 14:14:38")
/** */
public final class RefreshTokenMeta extends org.slim3.datastore.ModelMeta<jp.co.chat.model.RefreshToken> {

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<jp.co.chat.model.RefreshToken, java.util.Date> createdDate = new org.slim3.datastore.CoreAttributeMeta<jp.co.chat.model.RefreshToken, java.util.Date>(this, "createdDate", "createdDate", java.util.Date.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<jp.co.chat.model.RefreshToken, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<jp.co.chat.model.RefreshToken, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<jp.co.chat.model.RefreshToken> refreshToken = new org.slim3.datastore.StringAttributeMeta<jp.co.chat.model.RefreshToken>(this, "refreshToken", "refreshToken");

    private static final RefreshTokenMeta slim3_singleton = new RefreshTokenMeta();

    /**
     * @return the singleton
     */
    public static RefreshTokenMeta get() {
       return slim3_singleton;
    }

    /** */
    public RefreshTokenMeta() {
        super("RefreshToken", jp.co.chat.model.RefreshToken.class);
    }

    @Override
    public jp.co.chat.model.RefreshToken entityToModel(com.google.appengine.api.datastore.Entity entity) {
        jp.co.chat.model.RefreshToken model = new jp.co.chat.model.RefreshToken();
        model.setCreatedDate((java.util.Date) entity.getProperty("createdDate"));
        model.setKey(entity.getKey());
        model.setRefreshToken((java.lang.String) entity.getProperty("refreshToken"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        jp.co.chat.model.RefreshToken m = (jp.co.chat.model.RefreshToken) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("createdDate", m.getCreatedDate());
        entity.setProperty("refreshToken", m.getRefreshToken());
        entity.setProperty("slim3.schemaVersion", 1);
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        jp.co.chat.model.RefreshToken m = (jp.co.chat.model.RefreshToken) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        jp.co.chat.model.RefreshToken m = (jp.co.chat.model.RefreshToken) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        throw new IllegalStateException("The version property of the model(jp.co.chat.model.RefreshToken) is not defined.");
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
    }

    @Override
    protected void incrementVersion(Object model) {
    }

    @Override
    protected void prePut(Object model) {
    }

    @Override
    protected void postGet(Object model) {
    }

    @Override
    public String getSchemaVersionName() {
        return "slim3.schemaVersion";
    }

    @Override
    public String getClassHierarchyListName() {
        return "slim3.classHierarchyList";
    }

    @Override
    protected boolean isCipherProperty(String propertyName) {
        return false;
    }

    @Override
    protected void modelToJson(org.slim3.datastore.json.JsonWriter writer, java.lang.Object model, int maxDepth, int currentDepth) {
        jp.co.chat.model.RefreshToken m = (jp.co.chat.model.RefreshToken) model;
        writer.beginObject();
        org.slim3.datastore.json.Default encoder0 = new org.slim3.datastore.json.Default();
        if(m.getCreatedDate() != null){
            writer.setNextPropertyName("createdDate");
            encoder0.encode(writer, m.getCreatedDate());
        }
        if(m.getKey() != null){
            writer.setNextPropertyName("key");
            encoder0.encode(writer, m.getKey());
        }
        if(m.getRefreshToken() != null){
            writer.setNextPropertyName("refreshToken");
            encoder0.encode(writer, m.getRefreshToken());
        }
        writer.endObject();
    }

    @Override
    protected jp.co.chat.model.RefreshToken jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        jp.co.chat.model.RefreshToken m = new jp.co.chat.model.RefreshToken();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.Default decoder0 = new org.slim3.datastore.json.Default();
        reader = rootReader.newObjectReader("createdDate");
        m.setCreatedDate(decoder0.decode(reader, m.getCreatedDate()));
        reader = rootReader.newObjectReader("key");
        m.setKey(decoder0.decode(reader, m.getKey()));
        reader = rootReader.newObjectReader("refreshToken");
        m.setRefreshToken(decoder0.decode(reader, m.getRefreshToken()));
        return m;
    }
}