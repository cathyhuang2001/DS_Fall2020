# HashTable Discussion

Explain which of your implementations (between OpenAddressingHashMap and ChainingHashMap) 
is to use for the search engine.

- Which approaches did you try in the implementation of each hash table 
(e.g. probing strategies, rehashing strategies, etc), in what order, and why did you choose them? 
What specific tweaks to your implementation improved or made things worse? Include failed or 
abandoned attempts if any, and why. Summarize all the different ways you developed, evaluated 
and improved your hash tables.

- Include all the benchmarking data, results and analysis that contributed to your final 
decision on which implementation to use for the search engine.

- Provide an analysis of your benchmark data and conclusions. Why did you choose your final HashMap 
implementation as the best one? What results were surprising and which were expected?

---
I have decided to use the ChainingHashMap with load factor 0.75 for the search engine. I have tried linear probing 
with load factor 0.75 and 0.5 for OpenAddressingHashMap. The following table compares the outcomes of running
Driver on both cases:

               Linear Probing with 0.75         Linear Probing with 0.5
apache.txt       469ms, 111518kb                    475ms, 114942kb
newegg.txt       329ms, 67270kb                     316ms, 67591kb
jhu.txt          23ms, 1340kb                       24ms, 1340kb
random164.txt    1176ms, 136669kb                   1071ms, 151073kb
joanne.txt       16ms, 0kb                          16ms, 0kb
urls.txt         14ms, 0kb                          14ms, 0kb

From the data above we can see that in general, the memory space needed for linear probing with a larger load factor
such as 0.75 is smaller than that with a smaller load factor (0.5). However, when processing big data files such as
random164.txt, linear probing with load factor 0.75 takes more time than that with load factor 0.5. In fact, if the
data file is huge, then linear probing with a large load factor may take a long time because of primary clustering and
not resizing frequently. (When I submit OpenAddressingHashMap onto CodeGrade, the tests time out for load factor 0.75.)

Then I have tried implementing ChainingHashMap with array and linked list and run Driver also with load factor 0.5 and
0.75 respectively and obtained the following table:

                Chaining with 0.75                 Chaining with 0.5
apache.txt       479ms, 111524kb                    425ms, 114956kb
newegg.txt       321ms, 67251kb                     331ms, 67282kb
jhu.txt          24ms, 1340kb                       22ms, 1340kb
random164.txt    1040ms, 141227kb                   1095ms, 151139kb
joanne.txt       17ms, 0kb                          17ms, 0kb
urls.txt         14ms, 0kb                          14ms, 0kb

From the data above we can see that in general, the memory space needed for chaining with a larger load factor such as
0.75 is smaller than that with a smaller load factor (0.5). Surprisingly, when processing large data files such as
random164.txt, the time taken for ChainingHashMap with load factor 0.5 is longer than that with load factor 0.75. When
processing small data files, the time and memory space needed for both are similar. When processing relatively large
files such as apache.txt and newegg.txt, the time needed for both are also similar, but the memory space needed for
ChainingHashMap with load factor 0.75 is smaller.

For the two implementations, the structure with a larger load factor requires smaller memory space than that with a
smaller load factor as expected. However, while linear probing with a larger load factor consumes more time when
processing large data files, this situation does not apply to ChainingHashMap.

Now we can compare the data of "Chaining with 0.75" and "Linear Probing with 0.5". We can see that the chaining method
generally takes smaller memory space. Although the time needed for linear probing method is generally shorter when
processing small or large-ish files, the time needed for chaining to process large files such as random164.txt is
shorter. There is a high risk of clustering and merging clusters when processing large data files with linear probing,
and this increases the expected search time. Therefore, if we are unsure about the size of the data file, it may be
more efficient to use the ChainingHashMap.