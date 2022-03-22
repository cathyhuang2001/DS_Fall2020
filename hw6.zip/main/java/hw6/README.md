## Part A: Test-first development

1. Discuss the difficulties you encountered in testing rotations for Avl and
 Treap map implementations; what tests cases you used and why you chose those 
 particular examples. You are encouraged to draw little ASCII trees to 
 illustrate  the test cases.
 
 It is hard to control the outcome of the Treap map because the priority is randomly generated.
 I wrote a new constructor that takes in a Random as parameter so that I can pass a Random seed (0) to foresee
 the sequence of priorities in the TreapMapTest and make predictions of the outcome of changes.
 
 In both tests, I tested multiple insertions, removal of leaf, removal of node with one child, and removal of node with
 multiple children so that right rotation, left rotation and all their combinations can be performed. I have also tested
 the case when the newly inserted node becomes the new root, as well as the root is removed from the map to make sure
 that the structures of the AVLTree and Treap hold.
 

## Part D: Benching Word Counts

1. Note the results of running `Driver` over several different test data using 
various implementations of `Map`.  More importantly, *describe* your 
observations and try to *explain* (justify) them using your understanding of 
the code you're benchmarking. 
(in other words, *why* are the numbers as they are?)

              federalist01 (1510)       hotel_california(271)       moby_dick(168362)     pride_and_prejudice(99642)
BSTMap             36ms                        21ms                   317ms                     225ms
AVLTreeMap         39ms                        17ms                   365ms                     243ms
TreapMap           43ms                        17ms                   368ms                     246ms

The table above shows the result of running 'Driver' on each txt file over the three implementations of 'Map'. The data
inside () is the word count of each txt file.

The outcomes of running each txt file with AVLTreeMap and TreapMap are mostly very close or even the same. This is
because the time complexity of operations in AVLTreeMap and TreapMap is generally O(log n). However, since we need to
re-balance the AVLTreeMap each time we insert or delete, it may be time-consuming. Also, as the structure of AVLTreeMap
is strictly balanced, that of TreapMap is not because of the randomness in priorities. This randomness cannot ensure
that the TreapMap is balanced, but it also makes an extremely unbalanced structure highly unlikely, which is possible
in the case of BSTMap. The worst case time complexity of TreapMap is O(n).

The time complexity of searching, inserting and deleting in BSTMap is generally O(h) where h is the height of the BST.
In the worst case, the time complexity can be O(n). Therefore, the efficiency of BSTMap can vary based on the input.
When there are relatively few words in the text, AVLTreeMap and TreapMap may process the file faster, but when there are
more words in the text, there may be more re-balancing process in the AVLTreeMap and also balancing due to priorities
in the TreapMap. In this case, the BSTMap may process the files faster.
