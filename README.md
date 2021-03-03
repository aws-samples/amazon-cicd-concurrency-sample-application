## Concurrency issue code (with gradle) for unittest & codeguru
---
### Sample code for AWS CI/CD pipeline with CodeGuru & UnitTest to improve code quality

this code have a Concurrency issue and Credential issue.
if you want test and fix this code.

8 thread using put fuction in hashmap same time. 

![ex_screenshot](https://codequality.workshop.aws/images/structure.svg)

It covers simple branch build and deployment, UnitTest report, and CodeGuru which automatically performs code review when create pull-requests.

* All sample code committing develop branch.
* This code made for AWS CI/CD pipeline with CodeGuru & UnitTest to improve code quality. 

* if you want test this code visiting [here](https://codequality.workshop.aws/)


---
## Build & Unittest

```bash
./gradlew clean build   
```

## Security

See [CONTRIBUTING](CONTRIBUTING.md#security-issue-notifications) for more information.

## License

This library is licensed under the MIT-0 License. See the LICENSE file.

