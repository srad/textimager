package com.github.srad.metaquery.reader.type;

import com.google.gson.Gson;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;

abstract public class ElementType {
    final public Sofa sofa;
    final public String id;
    final public int begin;
    final public int end;

    final public static String TypeSeparator = ">>>>";

    final public HashMap<String, String> attr;

    final Gson gson = new Gson();

    /**
     * Lazy
     */
    private String extractedText = null;

    public ElementType(final Sofa sofa, final HashMap<String, String> attr) {
        this.attr = attr;
        this.sofa = sofa;
        this.id = attr.get("id");
        this.begin = Integer.valueOf(attr.get("begin"));
        this.end = Integer.valueOf(attr.get("end"));
    }

    public String getText() {
        if (extractedText == null) {
            extractedText = sofa.getText(this.begin, this.end);
        }
        return extractedText;
    }

    public String getName() {
        return this.getClass().getSimpleName();
    }

    public String getNormalizedText() {
        return getText().toLowerCase();
    }

    public String getTextWithType() {
        return String.format("%s%s%s", getClass().getSimpleName(), TypeSeparator, getNormalizedText());
    }

    public String getTypeName() {
        return getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return String.format("%s(id: %s, text: %s, begin: %s, end: %s)", getName(), id, getText(), begin, end);
    }

    public static QName getElementInfo() {
        throw new NotImplementedException();
    }

    public HashMap<String, String> toMap() {
        return toMap("");
    }

    public HashMap<String, String> toMap(final String keyPrefix) {
        // getId is lazy, becase the sofa element occurs late in xml stream
        final HashMap<String, String> map = new HashMap<>();
        //put(keyPrefix + "id", attr.get("id"));
        //put(keyPrefix + "sofa", sofa.getId());
        map.put(keyPrefix + "begin", attr.get("begin"));
        map.put(keyPrefix + "end", attr.get("end"));
        map.put(keyPrefix + "text", getText());

        return map;
    }

    public String toJson() {
        return gson.toJson(toMap());
    }
}
