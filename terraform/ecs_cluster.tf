resource "aws_ecs_cluster" "main" {
  name     = "file-processor-cluster"
  region   = "us-east-2"
  tags     = {}
  tags_all = {}
  configuration {
    execute_command_configuration {
      kms_key_id = null
      logging    = "DEFAULT"
    }
  }
  setting {
    name  = "containerInsights"
    value = "disabled"
  }
}
