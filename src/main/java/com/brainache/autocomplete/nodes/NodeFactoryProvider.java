

package com.brainache.autocomplete.nodes;

import com.brainache.autocomplete.nodes.Node.NodeFactory;

/**
 *
 * @author Brainight
 */
public class NodeFactoryProvider {

    public static <T> NodeFactory getNodeFactory(Class<T> clazz){
        NodeFactory nf = null;
        if(clazz.equals(LinkedListNode.class)){
            nf = (c) -> new LinkedListNode((char)c[0]);
        }else if(clazz.equals(TreeMapNode.class)){
            nf = (c) -> new TreeMapNode();
        }
        return nf;
    }
}
