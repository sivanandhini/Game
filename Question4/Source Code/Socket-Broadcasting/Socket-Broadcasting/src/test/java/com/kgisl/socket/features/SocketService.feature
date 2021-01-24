@SocketService

Feature: To check all the methods of socket service with valid and invalid unit testing data

@CreateJWTWithValidData
Scenario Outline: To create jwt encrypted string with valid data

Given secret key to encrypt message content
When we need to create jwt encrypted string with <id>, <issuer>, <subject> and <ttlMillis>
Then it should return created token

Examples:
         |   id   |    issuer   |   subject   |   ttlMillis  |
         |   aa   |    test     |    s2ub1    |     123      |
         |   bb   |    te3st    |    s3ub2    |     323      |
         |   a2y  |    tes3t    |    s3ub3    |     623      |
         
@CreateJWTWithInvalidData
Scenario Outline: To create jwt encrypted string with valid data

Given secret key to encrypt message content
When we need to create jwt encrypted string with <id>, <issuer>, <subject> and <ttlMillis>
Then it should throw an exception

Examples:
         |   id   |    issuer   |   subject  |   ttlMillis  |
         |   y    |    test1    |            |              |
         |   b9   |             |    sub2    |              |
         |   a23  |    test3    |    sub3    |              |

