resource "aws_ecs_task_definition" "worker" {
  container_definitions = jsonencode([{
    environment = [{
      name  = "DB_PASSWORD"
      value = var.db_password
      }, {
      name  = "SPRING_DATASOURCE_URL"
      value = "jdbc:postgresql://file-processor-db.cde0q0228hx3.us-east-2.rds.amazonaws.com:5432/postgres"
    }]
    environmentFiles = []
    essential        = true
    image            = "942440204471.dkr.ecr.us-east-2.amazonaws.com/file-processor/worker:latest"
    logConfiguration = {
      logDriver = "awslogs"
      options = {
        awslogs-create-group  = "true"
        awslogs-group         = "/ecs/file-processor-worker"
        awslogs-region        = "us-east-2"
        awslogs-stream-prefix = "ecs"
      }
      secretOptions = []
    }
    mountPoints    = []
    name           = "Main"
    portMappings   = []
    systemControls = []
    ulimits        = []
    volumesFrom    = []
  }])
  cpu                      = "512"
  enable_fault_injection   = false
  execution_role_arn       = "arn:aws:iam::942440204471:role/ecsTaskExecutionRole"
  family                   = "file-processor-worker"
  ipc_mode                 = null
  memory                   = "1024"
  network_mode             = "awsvpc"
  pid_mode                 = null
  region                   = "us-east-2"
  requires_compatibilities = ["FARGATE"]
  skip_destroy             = null
  tags                     = {}
  tags_all                 = {}
  task_role_arn            = "arn:aws:iam::942440204471:role/file-processor-task-role"
  track_latest             = false
  runtime_platform {
    cpu_architecture        = "ARM64"
    operating_system_family = "LINUX"
  }
}

resource "aws_ecs_task_definition" "api" {
  container_definitions = jsonencode([{
    environment = [{
      name  = "DB_PASSWORD"
      value = var.db_password
      }, {
      name  = "SPRING_DATASOURCE_URL"
      value = "jdbc:postgresql://file-processor-db.cde0q0228hx3.us-east-2.rds.amazonaws.com:5432/postgres"
    }]
    environmentFiles = []
    essential        = true
    image            = "942440204471.dkr.ecr.us-east-2.amazonaws.com/file-processor/api:latest"
    logConfiguration = {
      logDriver = "awslogs"
      options = {
        awslogs-create-group  = "true"
        awslogs-group         = "/ecs/file-processor-api"
        awslogs-region        = "us-east-2"
        awslogs-stream-prefix = "ecs"
      }
      secretOptions = []
    }
    mountPoints = []
    name        = "api"
    portMappings = [{
      appProtocol   = "http"
      containerPort = 8080
      hostPort      = 8080
      name          = "api-8080-tcp"
      protocol      = "tcp"
    }]
    systemControls = []
    ulimits        = []
    volumesFrom    = []
  }])
  cpu                      = "512"
  enable_fault_injection   = false
  execution_role_arn       = "arn:aws:iam::942440204471:role/ecsTaskExecutionRole"
  family                   = "file-processor-api"
  ipc_mode                 = null
  memory                   = "1024"
  network_mode             = "awsvpc"
  pid_mode                 = null
  region                   = "us-east-2"
  requires_compatibilities = ["FARGATE"]
  skip_destroy             = null
  tags                     = {}
  tags_all                 = {}
  task_role_arn            = "arn:aws:iam::942440204471:role/file-processor-task-role"
  track_latest             = false
  runtime_platform {
    cpu_architecture        = "ARM64"
    operating_system_family = "LINUX"
  }
}
