#!/bin/bash
echo -e "\033[0;32mDeploying updates to GitHub...\033[0m"

msg="Update site `date`"
if [[ "$1" == "-b" ]]
then
	cd /home/firo/blog
	cp -r ./archetypes ./config.toml ./deploy.sh ./layouts ./README.md ./static /home/firo/e/akasha/blog
	exit
elif [[ "$#" -ne "0" ]]
then
	msg="$@"
	echo $msg
fi
# Build the project. 
hugo # if using a theme, replace by `hugo -t <yourtheme>`

# Go To Public folder
cd public
# Add changes to git.
git add -A

# Commit changes.

git commit -m "$msg"

# Push source and build repos.
git push origin master

# Come Back
cd ..

