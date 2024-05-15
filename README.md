# Social Networks: summer term project x1

'Dapper' is a basic social network simulation program, which uses the Graph ADT as its foundation. This project was made as part of the summer term coursework, and the tasks are laid out below.

### Task 1: 
Give the density of the graph represented by the social network. Recall that the density of a directed graph is, where and respectively represent the set of nodes and directed edges.

### Task 2: 
Name a person who has the highest number of followers. In general, more than one person may have the same number of followers. If there is more than one person with the highest number of followers, then the name you give must be the one that is alphabetically first.

### Task 3: 
Name a person who follows the highest number of people. Again, if there is more than one such person, again, the name you give must be the one that is alphabetically first

### Task 4: 
A degree of separation is a measure of social distance between people. For example, people who are friends are separated by one degree. Two people who are not friends, but have a common friend are separated by two degrees.

For a person P in Dapper, we define people at two degrees of separation from P as follows.

    P’s followers are at one degree of separation from P.

    Followers of P’s followers who are neither P nor at one degree of separation from P are at two degrees of separation from P.

For the first person who appears in the input (for the input in Figure 1, this person is Raman), give the number of people at two degrees of separation from that person.

### Task 5: 
Give the median value for the number of followers in the network, that is, over the data set whose elements consist of the number of followers for each person in the network. Note that the median value is the middle element in a data set – half the data elements are smaller than the median value and half the data elements are larger. Make sure you handle the case when the number of elements is even, in which case you have no unique middle element, but two middle elements. CLARIFICATION: In such a case, you must average the two middle elements, which means that the answer may not be an integer.

### Task 6: 
In Dapper, a message originating from a person is automatically propagated to their followers and the followers’ followers, and so on (obviously in an acyclic manner; that is, a person doesn’t get the same message more than once). So, based on the social network of Figure 1, if Carla sends a message, then it is propagated to Anwar and Raman because they both follow Carla. Raman is Anwar’s only follower but he has already seen the message; Carla and Anwar are Raman’s only followers but they too have seen the message. So a message sent by Carla propagates to Anwar and Raman.

Imagine you are an advertiser (you yourself are outside the social network) who wants to spread information about a new product to as many people as possible in Dapper. However, the catch is you can enrol only one person in the network to spread information on your behalf. Whom would you enrol? Again, if you determine that two or more people are equally worthy of being enrolled, you must give the name that is alphabetically first.
