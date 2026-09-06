resource "aws_ecs_service" "worker" {
  availability_zone_rebalancing      = "ENABLED"
  cluster                            = "arn:aws:ecs:us-east-2:942440204471:cluster/file-processor-cluster"
  deployment_maximum_percent         = 200
  deployment_minimum_healthy_percent = 100
  desired_count                      = 0
  enable_ecs_managed_tags            = true
  enable_execute_command             = true
  force_delete                       = null
  force_new_deployment               = null
  health_check_grace_period_seconds  = 0
  iam_role                           = "/aws-service-role/ecs.amazonaws.com/AWSServiceRoleForECS"
  launch_type                        = "FARGATE"
  name                               = "file-processor-worker-service"
  platform_version                   = "LATEST"
  propagate_tags                     = "NONE"
  region                             = "us-east-2"
  scheduling_strategy                = "REPLICA"
  sigint_rollback                    = null
  tags                               = {}
  tags_all                           = {}
  task_definition                    = "file-processor-worker:2"
  triggers                           = {}
  wait_for_steady_state              = null
  deployment_circuit_breaker {
    enable   = true
    rollback = true
  }
  deployment_configuration {
    bake_time_in_minutes = "0"
    strategy             = "ROLLING"
  }
  deployment_controller {
    type = "ECS"
  }
  network_configuration {
    assign_public_ip = true
    security_groups  = ["sg-047dfdf5ca46115a1"]
    subnets          = ["subnet-023c0ac3533fea1be", "subnet-0669748d26f2ee057", "subnet-07d99a811156b42d7"]
  }
}

resource "aws_ecs_service" "api" {
  availability_zone_rebalancing      = "ENABLED"
  cluster                            = "arn:aws:ecs:us-east-2:942440204471:cluster/file-processor-cluster"
  deployment_maximum_percent         = 200
  deployment_minimum_healthy_percent = 100
  desired_count                      = 0
  enable_ecs_managed_tags            = true
  enable_execute_command             = true
  force_delete                       = null
  force_new_deployment               = null
  health_check_grace_period_seconds  = 0
  iam_role                           = "/aws-service-role/ecs.amazonaws.com/AWSServiceRoleForECS"
  launch_type                        = "FARGATE"
  name                               = "file-processor-api-service"
  platform_version                   = "LATEST"
  propagate_tags                     = "NONE"
  region                             = "us-east-2"
  scheduling_strategy                = "REPLICA"
  sigint_rollback                    = null
  tags                               = {}
  tags_all                           = {}
  task_definition                    = "file-processor-api:2"
  triggers                           = {}
  wait_for_steady_state              = null
  deployment_circuit_breaker {
    enable   = true
    rollback = true
  }
  deployment_configuration {
    bake_time_in_minutes = "0"
    strategy             = "ROLLING"
  }
  deployment_controller {
    type = "ECS"
  }
  network_configuration {
    assign_public_ip = true
    security_groups  = ["sg-047dfdf5ca46115a1"]
    subnets          = ["subnet-023c0ac3533fea1be", "subnet-0669748d26f2ee057", "subnet-07d99a811156b42d7"]
  }
}
