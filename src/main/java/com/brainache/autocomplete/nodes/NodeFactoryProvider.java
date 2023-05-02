

package com.brainache.autocomplete.nodes;

import com.brainache.autocomplete.nodes.Node.NodeFactory;

/**
 *
 * @author Brainight
 */
public class NodeFactoryProvider {

    public static <T> NodeFactory getNodeFactory(Node.NodeType nodeType){
        NodeFactory nf = null;
        if(nodeType == Node.NodeType.LinkedListNode){
            nf = (c) -> new LinkedListNode((char)c[0]);
        }else if(nodeType == Node.NodeType.TreeMapNode){
            nf = (c) -> new TreeMapNode();
        }
        return nf;
    }
}
