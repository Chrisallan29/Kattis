#!/bin/bash

# Set your repository path and folder to track
REPO_PATH="/Users/allanchris/Desktop/CS2040 Finals Practise"
TARGET_FOLDER="path/to/specific/folder"

# Navigate to the repository
cd "$REPO_PATH"

# Add changes from the target folder
git add "$TARGET_FOLDER"

# Commit the changes with a timestamp
git commit -m "Automated update from $TARGET_FOLDER: $(date)" || echo "No changes to commit"

# Push the changes to the main branch
git push origin main
