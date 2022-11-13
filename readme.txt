---------------------------------------------------------------------------------------
To make executable application run:
./gradlew packageDeb
it makes the deb package into /home/alexey/Work/QuadEq/build/compose/binaries/main/deb/

------------------------
Git Ignore:

1) add to gitignore:
.idea/
.gradle/
build/

2) in terminal run:
git rm -r --cached .idea
git rm -r --cached .gradle
git rm -r --cached build

3) run:
git commit
