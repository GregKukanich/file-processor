resource "aws_iam_role" "ecs_task_execution_role" {
  assume_role_policy = jsonencode({
    Statement = [{
      Action = "sts:AssumeRole"
      Effect = "Allow"
      Principal = {
        Service = "ecs-tasks.amazonaws.com"
      }
      Sid = ""
    }]
    Version = "2008-10-17"
  })
  description           = null
  force_detach_policies = false
  max_session_duration  = 3600
  name                  = "ecsTaskExecutionRole"
  path                  = "/"
  permissions_boundary  = null
  tags                  = {}
  tags_all              = {}
}

resource "aws_iam_role_policy_attachment" "ecs_task_execution_policy" {
  policy_arn = "arn:aws:iam::aws:policy/service-role/AmazonECSTaskExecutionRolePolicy"
  role       = "ecsTaskExecutionRole"
}

resource "aws_iam_role" "task_role" {
  assume_role_policy = jsonencode({
    Statement = [{
      Action = "sts:AssumeRole"
      Effect = "Allow"
      Principal = {
        Service = "ecs-tasks.amazonaws.com"
      }
      Sid = ""
    }]
    Version = "2012-10-17"
  })
  description           = "Allows ECS tasks to call AWS services on your behalf."
  force_detach_policies = false
  max_session_duration  = 3600
  name                  = "file-processor-task-role"
  path                  = "/"
  permissions_boundary  = null
  tags                  = {}
  tags_all              = {}
}

resource "aws_iam_role_policy" "task_permissions" {
  name = "file-processor-task-permissions"
  policy = jsonencode({
    Statement = [{
      Action   = ["s3:PutObject", "s3:GetObject"]
      Effect   = "Allow"
      Resource = ["arn:aws:s3:::file-processor-dev-942440204471-us-east-2-an/*"]
      Sid      = "Statement1"
      }, {
      Action   = ["sqs:SendMessage", "sqs:ReceiveMessage", "sqs:DeleteMessage", "sqs:GetQueueAttributes"]
      Effect   = "Allow"
      Resource = "arn:aws:sqs:us-east-2:942440204471:file-processor-queue"
    }]
    Version = "2012-10-17"
  })
  role = "file-processor-task-role"
}
