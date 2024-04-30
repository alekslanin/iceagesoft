Choosing the Right Map 

Having looked at HashMap and LinkedHashMap implementations previously and now TreeMap, it is important to make a brief comparison between the three to guide us on which one fits where.

A hash map is good as a general-purpose map implementation that provides rapid storage and retrieval operations. 
However, it falls short because of its chaotic and un-orderly arrangement of entries.
This causes it to perform poorly in scenarios where there is a lot of iteration as the entire capacity of the underlying array affects 
traversal other than just the number of entries.

A linked hash map possesses the good attributes of hash maps and adds order to the entries. 
It performs better where there is a lot of iteration because only the number of entries is taken into account regardless of capacity.

A tree map takes ordering to the next level by providing complete control over how the keys should be sorted. 
On the flip side, it offers worse general performance than the other two alternatives.

We could say a linked hash map reduces the chaos in the ordering of a hash map without incurring the performance penalty of a tree map.