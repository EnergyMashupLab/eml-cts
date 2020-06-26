This is a very short guide to using Travis CI with your GitHub hosted code repository

Prerequisites #
To start using Travis CI, make sure you have:

-  A GitHub account.
- Owner permissions for a project hosted on GitHub.

To get started with Travis CI #

1. Go to Travis-ci.com and Sign up with GitHub.
1. Accept the Authorization of Travis CI. You’ll be redirected to GitHub.
1. Click on your profile picture in the top right of your Travis Dashboard, click the green Activate button, and select the repositories you want to use with Travis CI.
1. Add a .travis.yml file to your repository to tell Travis CI what to do. The following example specifies a Ruby project that should be built with Ruby 2.2 and the latest versions of JRuby.
    ```  YAML
      language: ruby
      rvm:
        - 2.2
        - jruby
    ```
    The defaults for Ruby projects are bundle install to install dependencies, and rake to build the project.
1. Add the .travis.yml file to git, commit and push to trigger a Travis CI build:
    ``` YAML
      Travis only runs builds on the commits you push after you’ve added a .travis.yml file.
    ```
1. Check the build status page to see if your build passes or fails according to the return status of the build command by visiting Travis CI and selecting your repository.

Note 20191223: Deleted .github/workflows/maven.yml to clean up Jenkins/Travis false negative on pull.
