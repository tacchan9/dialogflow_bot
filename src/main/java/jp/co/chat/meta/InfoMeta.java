package jp.co.chat.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2018-02-21 14:14:38")
/** */
public final class InfoMeta extends org.slim3.datastore.ModelMeta<jp.co.chat.model.Info> {

    /** */
    public final org.slim3.datastore.StringAttributeMeta<jp.co.chat.model.Info> address = new org.slim3.datastore.StringAttributeMeta<jp.co.chat.model.Info>(this, "address", "address");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<jp.co.chat.model.Info, java.util.Date> createdDate = new org.slim3.datastore.CoreAttributeMeta<jp.co.chat.model.Info, java.util.Date>(this, "createdDate", "createdDate", java.util.Date.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<jp.co.chat.model.Info> header = new org.slim3.datastore.StringAttributeMeta<jp.co.chat.model.Info>(this, "header", "header");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<jp.co.chat.model.Info, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<jp.co.chat.model.Info, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<jp.co.chat.model.Info> sheetId = new org.slim3.datastore.StringAttributeMeta<jp.co.chat.model.Info>(this, "sheetId", "sheetId");

    private static final InfoMeta slim3_singleton = new InfoMeta();

    /**
     * @return the singleton
     */
    public static InfoMeta get() {
       return slim3_singleton;
    }

    /** */
    public InfoMeta() {
        super("Info", jp.co.chat.model.Info.class);
    }

    @Override
    public jp.co.chat.model.Info entityToModel(com.google.appengine.api.datastore.Entity entity) {
        jp.co.chat.model.Info model = new jp.co.chat.model.Info();
        model.setAddress((java.lang.String) entity.getProperty("address"));
        model.setCreatedDate((java.util.Date) entity.getProperty("createdDate"));
        model.setHeader((java.lang.String) entity.getProperty("header"));
        model.setKey(entity.getKey());
        model.setSheetId((java.lang.String) entity.getProperty("sheetId"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        jp.co.chat.model.Info m = (jp.co.chat.model.Info) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("address", m.getAddress());
        entity.setProperty("createdDate", m.getCreatedDate());
        entity.setProperty("header", m.getHeader());
        entity.setProperty("sheetId", m.getSheetId());
        entity.setProperty("slim3.schemaVersion", 1);
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        jp.co.chat.model.Info m = (jp.co.chat.model.Info) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        jp.co.chat.model.Info m = (jp.co.chat.model.Info) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        throw new IllegalStateException("The version property of the model(jp.co.chat.model.Info) is not defined.");
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
        jp.co.chat.model.Info m = (jp.co.chat.model.Info) model;
        writer.beginObject();
        org.slim3.datastore.json.Default encoder0 = new org.slim3.datastore.json.Default();
        if(m.getAddress() != null){
            writer.setNextPropertyName("address");
            encoder0.encode(writer, m.getAddress());
        }
        if(m.getCreatedDate() != null){
            writer.setNextPropertyName("createdDate");
            encoder0.encode(writer, m.getCreatedDate());
        }
        if(m.getHeader() != null){
            writer.setNextPropertyName("header");
            encoder0.encode(writer, m.getHeader());
        }
        if(m.getKey() != null){
            writer.setNextPropertyName("key");
            encoder0.encode(writer, m.getKey());
        }
        if(m.getSheetId() != null){
            writer.setNextPropertyName("sheetId");
            encoder0.encode(writer, m.getSheetId());
        }
        writer.endObject();
    }

    @Override
    protected jp.co.chat.model.Info jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        jp.co.chat.model.Info m = new jp.co.chat.model.Info();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.Default decoder0 = new org.slim3.datastore.json.Default();
        reader = rootReader.newObjectReader("address");
        m.setAddress(decoder0.decode(reader, m.getAddress()));
        reader = rootReader.newObjectReader("createdDate");
        m.setCreatedDate(decoder0.decode(reader, m.getCreatedDate()));
        reader = rootReader.newObjectReader("header");
        m.setHeader(decoder0.decode(reader, m.getHeader()));
        reader = rootReader.newObjectReader("key");
        m.setKey(decoder0.decode(reader, m.getKey()));
        reader = rootReader.newObjectReader("sheetId");
        m.setSheetId(decoder0.decode(reader, m.getSheetId()));
        return m;
    }
}