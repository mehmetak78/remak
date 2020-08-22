- Rule Engine Mehmet AK

- Interpreter is working on a binary tree.

- If the operand has a lower or equal priority
	- Add new node it to the current node's parent.
	- Add the current node o the left child of new node
	
- If the operand has a higher priority
	- Search recurisvely for a node with a lower or equal priority
	- Add the new node to the right child of the found node.
	
- If there is a parantheses
	- Create a subtree until the end of the parantheses
	- Add this subtree to the current node's deepest right child
	
YEDEK 1