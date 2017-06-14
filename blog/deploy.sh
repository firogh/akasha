#!/bin/bash

msg="Update site `date`"
if [[ "$1" == "-b" ]]
then
	cd /home/firo/blog
	mkdir -p /home/firo/e/blog/
	rm -rf /home/firo/e/blog/*
	echo -e "\033[0;32mBackup blog...\033[0m"
	cp -r ./archetypes ./config.toml ./deploy.sh ./layouts ./README.md ./static /home/firo/e/blog
	exit
elif [[ "$#" -ne "0" ]]
then
	msg="$@"
	echo $msg
fi
echo -e "\033[0;32mDeploying updates to GitHub...\033[0m"
# Build the project. 
hugo # if using a theme, replace by `hugo -t <yourtheme>`

# Go To Public folder
cd public
# Add changes to git.
git add  .

# Commit changes.

git commit -m "$msg"

# Push source and build repos.
git push origin master

# Come Back
cd ..

