# critical-embedded-mas
Version of embedded-mas addapted for Jason(C)

To work properly, it is necessary to create a new class that extends DefaultEmbeddedAgArch like, for example, DemoEmbeddedAgentArch. In such new class it is necessary to override the method perceiveCBS() with the given code. Moreover, currently, one must also declare the following variable:
	private int lastCPval = -12345;  

An example of such class, ready to be used, is given in git-URL.

Another thing is to edit the YAML file with the ROS topics and action, selecting one of the topic being listened to be the critical event. Such topic should have 'beliefName: critical_percept'. 