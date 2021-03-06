package com.github.srad.metaquery.reader.type;

import javax.xml.namespace.QName;
import java.util.HashMap;

final public class Sentence extends ElementType {
    public Sentence(final Sofa sofa, final HashMap<String, String> attr) {
        super(sofa, attr);
    }

    public static QName getElementInfo() {
        return new QName("http:///de/tudarmstadt/ukp/dkpro/core/api/segmentation/type.ecore", "Sentence");
    }
}
