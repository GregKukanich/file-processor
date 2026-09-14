# file-processor

A small file processing app I built to get hands on with AWS. I work mostly on the backend/Java side and wanted real experience with cloud infra instead of just reading about it, so I built this end to end myself — app code, Docker, and the AWS infrastructure (Terraform, CI/CD, all of it).

Not trying to be a full production system, kept the scope small on purpose so I could actually finish it and focus on doing the core pieces right.

## What it does

Upload a CSV, it gets stored in S3, dropped on an SQS queue, and picked up by a separate worker service that parses it and writes the results to Postgres.

```
Client -> Spring Boot API -> S3 (upload) + Postgres (job status)
                 S3 -> SQS -> Worker -> Postgres
```

## Stack

- Java / Spring Boot
- PostgreSQL (RDS)
- AWS: S3, SQS, ECS/Fargate, ECR, RDS, IAM
- Terraform (all infra is code, no console clicking)
- GitHub Actions (CI + CD, deploys to ECS on push to main via OIDC — no long-lived AWS keys)
- Docker

## Why it's built this way

Went local-first and added cloud pieces one at a time (local DB -> CSV processing -> S3 -> SQS -> worker -> Docker -> ECS/RDS -> Terraform -> CI/CD) instead of starting with the full AWS setup. Made it a lot easier to actually understand what each piece was doing instead of copy pasting a big infra setup at the start.

Deliberately left out: Kubernetes, Kafka, Redis, microservices, multi cloud, anything else that would turn this into a bigger project than it needs to be for what I was trying to learn.

## Status

App and infra are working end to end. CloudWatch monitoring/alarms is the next thing I'm adding.
