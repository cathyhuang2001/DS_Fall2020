# Mystery Iterator

1. What type of traversal is implemented by the mystery iterator?
In-order Traversal is implemented.

2. Is mystery iterator a recursive or an iterative implementation?
It is an iterative implementation.

3. What is the auxiliary space complexity (in asymptotic notation) of the mystery iterator? 
Briefly elaborate (a few sentences is enough!)
The auxiliary space complexity of the mystery iterator is O(1). No extra space is needed through the traversal,
as only the original binary search tree is modified and then restored back to original.

4. Describe (briefly) the strategy (algorithm) behind the mystery iterator. 
(One or two paragraphs at most.)

We initiate the root as the current node curr. While current node is not null, if it has no left child, we return data
of the current node and we go to its right child.
If current has a left child, then find its inorder predecessor, which is the rightmost node in the current left subtree
or the node whose right child is current, with the getPredecessor() function. We make current as the right child of its
inorder predecessor if its predecessor does not have a right child and proceed to current's left child. Else we revert
the changes by fixing the right child of predecessor to restore the original tree. Then we return the data of current
and proceed to its right child.





# Selection problem

1. Assuming the BinarySearchTree is balanced, what is the time complexity (in asymptotic notation) 
of the 'findByPlace' method? Briefly elaborate. 

The time complexity is O(log n) in the worst case if the BST is balanced. The method requires O(d) time where d depends
on the depth of the node we are looking for. Therefore, if the BST is balanced, the time complexity is O(log n).