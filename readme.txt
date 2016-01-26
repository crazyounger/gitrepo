git is a version contrl system
git is a free software

git init  
git add filename 
git commit -m "something to show change "

git status 
git diff
git diff HEAD --readme.txt


git log 

git reset --hard HEAD^

git checkout  --readme.txt  

=================================================================
how to combind remote git repo  whith local git repo

git remote add origin git@github.com:michaelliao/learngit.git

git push -u origin master

git push origin master 

==================================================================

git branch newbranch //new 

git branch  //list branch 

git checkout testbranch // chekcout to branch 

git checkout -b testbranch  // new and checkout 

git branch -d testbranch // delete branch 



git merge testbrach

=================================================================

git stash
git stash list 
git stash apply 
git stash drop
git stash pop == git stash apply + git stash drop 
================================================================
git branch manager : master dev feature bug 


--------------------------
git checkout -b dev 
git add readme.txt
git commit -m 'git checkout branch'
change content on master branch 
change content on dev branch

 




