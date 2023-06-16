package org.hackerrank.java.interview;

import org.junit.Test;

import java.util.*;

public class SolutionOptionsTree {

    @Test
    public void testSelection() {
        combinationSum(new int[]{2, 3, 6, 7}, 7);
        combinationSum(new int[]{2, 3, 5}, 8);
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Set<TreeNode> leaves = new HashSet<>();
        buildTree(candidates, target, new TreeNode(-1, null), leaves);

        Set<List<Integer>> set = new HashSet<>();
        for (TreeNode leaf : leaves) {
            set.add(climbUp(leaf));
        }
        return set.stream().toList();
    }

    void buildTree(int[] candidates, int target, TreeNode node, Set<TreeNode> leaves) {
        for (int candidate : candidates) {
            int nextTarget = target - candidate;
            if (nextTarget >= 0) {
                TreeNode child = new TreeNode(candidate, node);
                node.children.add(child);
                if (nextTarget == 0) {
                    leaves.add(child);
                } else {
                    buildTree(candidates, nextTarget, child, leaves);
                }
            }
        }
    }

    List<Integer> climbUp(TreeNode leaf) {
        List<Integer> candidates = new ArrayList<>();
        while (leaf.parent != null) {
            candidates.add(leaf.candidate);
            leaf = leaf.parent;
        }
        return candidates.stream().sorted().toList();
    }

    private static class TreeNode {
        private final int candidate;
        private final TreeNode parent;
        private final List<TreeNode> children = new ArrayList<>();

        TreeNode(int candidate, TreeNode parent) {
            this.candidate = candidate;
            this.parent = parent;
        }
    }

}
