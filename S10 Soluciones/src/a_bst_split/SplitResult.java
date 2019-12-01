package a_bst_split;

import base.IntBinaryTreeNode;

public class SplitResult {
	public IntBinaryTreeNode lesser;
	public IntBinaryTreeNode greater;

	public SplitResult(IntBinaryTreeNode lesser, IntBinaryTreeNode greater) {
		this.lesser = lesser;
		this.greater = greater;
	}
}
