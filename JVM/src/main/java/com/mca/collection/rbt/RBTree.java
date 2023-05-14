package com.mca.collection.rbt;

/**
 * ClassName: RBTree
 * Package: com.mca.collection.rbt
 * Description: 手写红黑树
 * 1、插入数据
 * 2、删除数据
 * 3、遍历数据
 *
 * @Author: yujie.qin
 * @Create: 2023/3/24 - 15:07
 * @version: v1.0
 */
public class RBTree<K extends Comparable<K>, V> {

    // color constant
    private static final boolean RED = false;
    private static final boolean BLACK = false;

    // current RBTree root node
    private RBTNode root;

    public RBTNode getRoot() {
        return root;
    }

    public void setRoot(RBTNode root) {
        this.root = root;
    }

    // 1、rotate and change color
    // left rotate
    /**
     *      p               pr
     *     /\               /\
     *   pl  pr    ==>    p  prr
     *      /\           /\
     *    prl prr      pl prl
     * 左旋的时候：
     * p - pl 和 pr - prr 这些节点的指向没有变化
     * pr - prl 的指向需要改变为 p - prl
     * 然后改变 p ,pr等节点的父子关系，和祖父子关系
     * */
    public void leftRotate(RBTNode p){
        if (p != null){
            // step1. 获取需要旋转节点的右子节点
            RBTNode pr = p.right;
            // 表示如果旋转节点 p 的右子节点有左子节点，需要把该节点变为旋转节点的右子节点
            // 设置 p - prl
            p.right = pr.left;
            if (pr.left != null){
                pr.left.parent = p;
            }

            // step2. 判断旋转节点是在其父节点的左侧还是右侧
            // 不管旋转节点是否有父节点，都把旋转节点的父节点赋值给旋转节点右子节点的父节点
            pr.parent = p.parent;
            if (p.parent == null){
                // 如果旋转节点的父节点是null，则旋转节点是 root 节点
                this.root = pr;
            } else if (p.parent.left == p){
                // 旋转节点父节点的左子节点 是旋转节点，说明旋转节点在其父节点的左侧
                p.parent.left = pr;
            } else {
                // 旋转节点父节点的右子节点 是旋转节点，说明旋转节点在其父节点的右侧
                p.parent.right = pr;
            }

            // step3. 设置p 和 pr 的关系
            pr.left = p;
            p.parent = pr;
        }
    }

    // right rotate
    /**
     *       p               pl
     *      /\               /\
     *    pl  pr   ==>     pll p
     *   /\                   /\
     * pll plr             plr pr
     * 左旋的时候：
     * p - pr 和 pl - pll 这些节点的指向没有变化
     * pl - plr 的指向需要改变为 p - prl
     * 然后改变 p ,pl等节点的父子关系，和祖父子关系
     * */
    public void rightRotate(RBTNode p){
        if (p != null){
            // step1. 获取需要旋转节点的左子节点
            RBTNode pl = p.left;
            // 设置 p - plr
            p.left = pl.right;
            if (pl.right != null){
                pl.right.parent = p;
            }

            // step2. 判断旋转节点是在其父节点的左侧还是右侧
            // 不管旋转节点是否有父节点，都把旋转节点的父节点赋值给旋转节点右子节点的父节点
            pl.parent = p.parent;
            if (p.parent == null){
                // 如果旋转节点的父节点是null，则旋转节点是 root 节点，旋转之后 pl 是root节点
                this.root = pl;
            } else if (p.parent.right == p){
                // 旋转节点父节点的左子节点 是旋转节点，说明旋转节点在其父节点的左侧
                p.parent.right = pl;
            } else {
                // 旋转节点父节点的右子节点 是旋转节点，说明旋转节点在其父节点的右侧
                p.parent.left = pl;
            }

            // step3. 设置p 和 pl 的关系
            pl.right = p;
            p.parent = pl;
        }
    }

    // 2、add data
    // ==> a. ordinary binary tree put
    // ==> b. the RB Tree Balanced, due to rotate and change color
    public void put(K key, V value){
        // step 1. 向一个普通的二叉查找树中插入节点
        // 记录当前节点
        RBTNode t = this.root;
        if (t == null){
            // t == null 说明整个红黑树是空的，那么插入的节点就是 root 节点，root 节点的父节点为空
            this.root = new RBTNode<>(key, value == null ? key : value, null);
            // root 节点的颜色为黑色
            this.root.color = BLACK;
            return;
        }
        if (key == null){
            throw new NullPointerException();
        }

        // 记录需要插入节点的父节点
        RBTNode parent;
        int i; // 记录插入节点的位置， i < 0 left ; i > 0 right
        // t != null 说明有 root 节点，添加一个节点需要先比较大小
        // 循环遍历节点比较，直到有相同节点或没有节点为止
        do {
            parent = t;
            i = key.compareTo((K) t.getKey());
            if (i < 0) {
                // i < 0，插入的节点比当前节点小，继续向左寻找
                t = t.left;
            } else if (i > 0) {
                // i > 0，插入的节点比当前节点大，继续向右寻找
                t = t.right;
            } else {
                // i == 0，说明插入的key和根节点一样
                // 覆盖原来的值
                t.setValue(value == null ? key : value);
                return;
            }
        } while (t != null);
        // 循环结束，已经找到需要插入节点的位置
        // 创建需要插入的节点
        RBTNode<K, Object> e = new RBTNode<>(key, value, parent);
        // 判断 e 应该在左右哪个子树上
        if (i < 0) {
            parent.left = e;
        } else {
            parent.right = e;
        }

        // step 2. 调整平衡，旋转 + 变色来实现
        fixAfterPut(e);
    }

    /**
     * 调整节点的平衡
     * 根据2-3-4树对比红黑树分析：
     * 1、2-3-4树：是一个2节点，插入一个节点后变成一个3节点
     *     红黑树：给一个黑色节点添加一个红色节点，这种情况不需要处理
     *
     * 2、2-3-4树：是一个3节点，插入一个节点后变成一个4节点，会有3个元素，会有6种情况
     *            其中【根左左，根左右，根右左，根右右】需要调整，其中【左根右】有 2 种，不需要处理
     *     红黑树：根左左 --> 右旋 + 变色
     *            根右右 --> 左旋 + 变色
     *            根左右 --> 先左旋，再右旋 + 变色
     *            根右左 --> 先右旋，再左旋 + 变色
     *
     * 3、2-3-4树：是一个4节点，插入一个节点后发生裂变，中间元素升为父节点，插入的元素会和其中一个子节点合并
     *     红黑树：只需要变色处理就可以
     * */
    private void fixAfterPut(RBTNode<K, Object> e) {
        // step1. 插入的节点都设置成红节点
        e.color = RED;

        // step2. 对红黑树进行旋转 + 变色
        while (e != null && e != root && e.parent.color == RED) {
            // 找到所有需要调整处理的情况
            if (parentOf(e) == parentOf(parentOf(e)).left) {
                // 当前节点的父节点是祖父节点的左子节点
                // 当前节点父节点的兄弟节点（叔叔节点）
                RBTNode uncleNode = rightOf(parentOf(parentOf(e)));
                if (colorOf(uncleNode) == RED) {
                    // 说明当前插入节点的叔叔节点是存在的，属于上面分析的情况3
                    // 变色，父节点和叔叔节点变黑色，祖父节点变红色
                    setColor(parentOf(e), BLACK);
                    setColor(uncleNode, BLACK);
                    setColor(parentOf(parentOf(e)), RED);
                    // 再以祖父节点为基准递归处理向上的平横
                    e = parentOf(parentOf(e));
                } else {
                    // 说明当前插入节点的叔叔节点是不存在的，属于上面分析的情况2
                    if (e == parentOf(e).right) {
                        // 以当前节点的父节点为旋转点，进行一次左旋操作
                        e = parentOf(e);
                        leftRotate(e);
                    }
                    // 父节点变黑，祖父节点变红
                    setColor(parentOf(e), BLACK);
                    setColor(parentOf(parentOf(e)), RED);
                    // 接下来再根据祖父节点做右旋操作，调整平衡
                    rightRotate(parentOf(parentOf(e)));
                }
            } else {
                // 当前节点的父节点是祖父节点的右子节点
                // 当前节点父节点的兄弟节点（叔叔节点）
                RBTNode uncleNode = leftOf(parentOf(parentOf(e)));
                if (colorOf(uncleNode) == RED) {
                    // 说明当前插入节点的叔叔节点是存在的，属于上面分析的情况3
                    // 变色，父节点和叔叔节点变黑色，祖父节点变红色
                    setColor(parentOf(e), BLACK);
                    setColor(uncleNode, BLACK);
                    setColor(parentOf(parentOf(e)), RED);
                    // 递归处理祖父节点和其父节点的颜色问题
                    e = parentOf(parentOf(e));
                } else {
                    // 说明当前插入节点的叔叔节点是不存在的，属于上面分析的情况2
                    if (e == parentOf(e).left) {
                        // 需要一个右旋操作
                        e = parentOf(e);
                        rightRotate(e);
                    }
                    // 接下来再根据祖父节点做左旋操作
                    setColor(parentOf(e), BLACK);
                    setColor(parentOf(parentOf(e)), RED);
                    leftRotate(parentOf(parentOf(e)));
                }
            }
        }

        // step3. root 节点都设置成黑节点
        this.root.setColor(BLACK);
    }

    private boolean colorOf(RBTNode node){
        return node == null ? BLACK : node.color;
    }

    private RBTNode parentOf(RBTNode node){
        return node != null ? node.parent : null;
    }

    private RBTNode leftOf(RBTNode node){
        return node != null ? node.left : null;
    }

    private RBTNode rightOf(RBTNode node){
        return node != null ? node.right : null;
    }

    private void  setColor(RBTNode node, boolean color){
        if (node != null){
            node.setColor(color);
        }
    }

    // 3、delete data
    /**
     * 红黑树的节点删除
     * 1、节点删除 --> 普通二叉查找树的节点删除操作
     * 2、通过旋转 + 变色来调整平衡
     *
     * */
    public V remove(K key){
        // step1. 通过 key 在红黑树上找到对应的节点
        RBTNode p = this.getNode(key);
        if (p == null){
            return null;
        }

        // step2. 返回删除节点的value
        V oldValue = (V) p.getValue();

        //step3. 具体删除节点的方法
        deleteNode(p);

        return oldValue;
    }

    /**
     * 具体删除节点的方法
     * 通过普通二叉查找树删除节点分析结果：
     * 1、删除的节点无子结点 --> 直接删除
     * 2、删除的节点有一个子节点 --> 直接用子节点替代
     * 3、删除的节点有2个子节点 --> 找到删除节点的前驱节点或后继节点替代
     *   前驱节点查找方法：先查找当前节点的左子节点，然后循环查到左子节点的右子节点，直至右子节点无子节点
     *                   如果当前接节点没有左子节点，查找当前节点的父节点，然后判断父节点是不是祖父节点的右子节点，如果是，则祖父节点是当前节点的前驱节点
     *   后继节点查找方法：先查找当前节点的右子节点，然后循环查找右子节点的左子节点，直至左子节点无子节点
     *                   如果当前接节点没有右子节点，查找当前节点的父节点，然后判断父节点是不是祖父节点的左子节点，如果是，则祖父节点是当前节点的后继节点
     *
     * 删除节点时可以选择降级处理，如果删除的节点有2个子节点，找到前驱 / 后继节点后，用前驱 / 后继节点的值替换到需要删除的节点
     * 然后再处理前驱 / 后继节点，如果前驱 / 后继节点也有2个子节点，重复上面操作，直至最后需要处理的节点无子节点，就可以直接删除了
     * 降级处理：3 --> 2 --> 1 --> 直接删除
     * */
    private void deleteNode(RBTNode p) {
        // 1、删除的节点有2个子节点
        if (leftOf(p) != null && rightOf(p) != null){
            // 查找删除节点的前驱 / 后继节点
            RBTNode predecessor = predecessor(p);
            // 通过前驱节点的信息覆盖需要删除的节点
            p.key = predecessor.key;
            p.value = predecessor.value;
            // 覆盖信息后，需要删除的节点从 p 变成了 predecessor
            p = predecessor;
        }
        // 找到替换的节点
        RBTNode replacement = p.left != null ? p.left : p.right;
        if (replacement != null){
            // 2、删除的节点有一个子节点
            // 将替代节点的父节点改成删除节点的父节点
            replacement.parent = p.parent;
            if (parentOf(p) == null) {
                // p 是 root 节点
                root = replacement;
            } else if (parentOf(p).left == p) {
                // p 是左子节点
                parentOf(p).left = replacement;
            } else {
                // p 是右子节点
                parentOf(p).right = replacement;
            }
            // 处理删除节点的关联关系，等待GC
            p.parent = p.left = p.right = null;
            // 考虑是否需要调整平衡
            if (colorOf(p) == BLACK){
                // 需要调整红黑树的平衡
                fixAfterRemove(replacement);
            }
        }else if (p.parent == null){
            // p节点是root节点
            root = null;
        }else {
            // 3、删除的节点无子结点
            // 先调整红黑树的平衡，再删除节点
            if (p.color == BLACK){
                // 需要调整红黑树的平衡
                fixAfterRemove(p);
            }
            if (p.parent != null){
                if (leftOf(parentOf(p)) == p){
                    parentOf(p).left = null;
                }else {
                    parentOf(p).right = null;
                }
                p = null;
            }
        }
    }

    /**
     * 删除节点后的调整操作
     * 根据分析2-3-4树删除操作后得出三种情况：
     * 1、删除3、4节点 自己能搞定的
     * 2、删除2节点 自己搞不定，需要兄弟节点借， 兄弟节点借 --> 父亲节点下来，兄弟节点找一个节点替换掉父亲节点的位置
     * 3、删除2节点 自己搞不定，需要兄弟借  兄弟不借
     *
     * @param del
     * @return
     * */
    private void fixAfterRemove(RBTNode del) {
        while (del != root && colorOf(del) == BLACK){
            // 判断 del 是父节点的左孩子还是右孩子
            if (del == leftOf(parentOf(del))){
                // del 是父节点的左孩子，兄弟节点是父节点的右孩子
                RBTNode brother = parentOf(del).right;
                // 判断找到的兄弟节点是不是是不是真的兄弟节点
                if (colorOf(brother) == RED){
                    // 找到的不是真正的兄弟节点，需要一次变色 + 旋转
                    setColor(brother, BLACK);
                    setColor(parentOf(del), RED);
                    leftRotate(parentOf(del));
                    del = parentOf(del).right;
                }
                // 当兄弟节点一个子节点都没有的情况下 不借
                if(colorOf(leftOf(brother)) == BLACK && colorOf(rightOf(brother)) == BLACK){
                    // 3.兄弟节点 不借
                    setColor(brother, RED);
                    del = parentOf(del);
                }else{
                    // 2.兄弟节点 借
                    // 如果兄弟节点的子节点是其左子节点 需要先变色 完成右转一次
                    if(colorOf(rightOf(brother)) == BLACK){
                        // 右侧子节点为空，那么左侧子节点肯定不为空
                        setColor(brother, RED);
                        setColor(leftOf(brother), BLACK);
                        rightRotate(brother);
                        brother = rightOf(parentOf(del));
                    }
                    // 需要根据父节点做一次左旋操作 变色
                    setColor(brother, colorOf(parentOf(del)));
                    setColor(parentOf(del), BLACK);
                    setColor(rightOf(brother), BLACK);
                    // 左旋操作
                    leftRotate(parentOf(del));
                    del = root; // 结束循环  针对情况3的处理
                }
            }else {
                // del 是父节点的右孩子，兄弟节点是父节点的左孩子
                RBTNode brother = parentOf(del).left;
                // 判断找到的兄弟节点是不是是不是真的兄弟节点
                if (colorOf(brother) == RED){
                    // 找到的不是真正的兄弟节点，需要一次变色 + 旋转
                    setColor(brother, BLACK);
                    setColor(parentOf(del), RED);
                    rightRotate(parentOf(del));
                    del = parentOf(del).left;
                }
                // 当兄弟节点一个子节点都没有的情况下 不借
                if(colorOf(leftOf(brother)) == BLACK && colorOf(rightOf(brother)) == BLACK){
                    // 3.兄弟节点 不借
                    setColor(brother, RED);
                    del = parentOf(del);
                }else{
                    // 2.兄弟节点 借
                    // 如果兄弟节点的子节点是其右子节点 需要先变色 完成左旋一次
                    if(colorOf(leftOf(brother)) == BLACK){
                        // 左子节点为空，那么右子节点肯定不为空
                        setColor(brother, RED);
                        setColor(leftOf(brother), BLACK);
                        leftRotate(brother);
                        brother = leftOf(parentOf(del));
                    }
                    // 需要根据父节点做一次左旋操作 变色
                    setColor(brother, colorOf(parentOf(del)));
                    setColor(parentOf(del), BLACK);
                    setColor(leftOf(brother), BLACK);
                    // 右旋操作
                    rightRotate(parentOf(del));
                    del = root; // 结束循环  针对情况3的处理
                }
            }
        }
        // 情况1： 替换的节点为红色，我们只需要变色为黑色即可
        setColor(del ,BLACK);
    }

    /**
     * 查询当前节点的前驱节点
     * @param node
     * @return
     * */
    private RBTNode predecessor(RBTNode node) {
        if (node == null){
            return null;
        } else if (leftOf(node) != null){
            RBTNode leftChild = leftOf(node);
            while (leftChild.right != null){
                leftChild = leftChild.right;
            }
            return leftChild;
        } else {
            // 当前节点没有左子节点的情况
            // 查找当前节点的父节点，然后判断父节点是不是祖父节点的右子节点，如果是，则祖父节点是当前节点的前驱节点
            // 记录当前节点的父节点
            RBTNode parent = parentOf(node);
            // 记录动态父节点的子节点
            RBTNode child = node;
            while (parent != null && child == parent.left){
                child = parent;
                parent = parentOf(parent);
            }
            return parent;
        }
    }

    /**
     * 查询当前节点的后继节点
     * @param node
     * @return
     * */
    private RBTNode succcessor(RBTNode node) {
        if (node == null){
            return null;
        } else if (rightOf(node) != null){
            RBTNode rightChild = rightOf(node);
            while (rightChild.left != null){
                rightChild = rightChild.left;
            }
            return rightChild;
        } else {
            // 当前节点没有右子节点的情况
            // 查找当前节点的父节点，然后判断父节点是不是祖父节点的左子节点，如果是，则祖父节点是当前节点的前驱节点
            // 记录当前节点的父节点
            RBTNode parent = parentOf(node);
            // 记录动态父节点的子节点
            RBTNode child = node;
            while (parent != null && child == parent.right){
                child = parent;
                parent = parentOf(parent);
            }
            return parent;
        }
    }

    /**
     * 根据 key 在红黑树上查找到对应的节点信息
     * @param key
     * @return
     * */
    private RBTNode getNode(K key) {
        RBTNode node = this.root;
        while (node != null) {
            int compare = key.compareTo((K) node.getKey());
            if (compare > 0) {
                // key 大，则在右边
                node = node.right;
            } else if (compare < 0) {
                // key 小，则在左边
                node = node.left;
            } else {
                // 找到节点
                return node;
            }
        }
        return null;
    }

    // 4、query data

    /**
     * RBTree data node
     *
     * */
    static class RBTNode<K extends Comparable<K>, V> {
        private K key; // current node's key
        private V value; // current node's value
        private RBTNode parent; // current node's parent node
        private RBTNode left; // current node's left child node
        private RBTNode right; // current node's right child node
        private boolean color; // current node's color

        public RBTNode() {
        }

        public RBTNode(K key, V value, RBTNode parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        public RBTNode(K key, V value, RBTNode parent, RBTNode left, RBTNode right, boolean color) {
            this.key = key;
            this.value = value;
            this.parent = parent;
            this.left = left;
            this.right = right;
            this.color = color;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public RBTNode getParent() {
            return parent;
        }

        public void setParent(RBTNode parent) {
            this.parent = parent;
        }

        public RBTNode getLeft() {
            return left;
        }

        public void setLeft(RBTNode left) {
            this.left = left;
        }

        public RBTNode getRight() {
            return right;
        }

        public void setRight(RBTNode right) {
            this.right = right;
        }

        public boolean getColor() {
            return color;
        }

        public void setColor(boolean color) {
            this.color = color;
        }
    }
}
