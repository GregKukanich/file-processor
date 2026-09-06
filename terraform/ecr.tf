# __generated__ by Terraform
# Please review these resources and move them into your main configuration files.

# __generated__ by Terraform from "file-processor/api"
resource "aws_ecr_repository" "api" {
  force_delete         = null
  image_tag_mutability = "MUTABLE"
  name                 = "file-processor/api"
  region               = "us-east-2"
  tags                 = {}
  tags_all             = {}
  encryption_configuration {
    encryption_type = "AES256"
  }
  image_scanning_configuration {
    scan_on_push = false
  }
}

# __generated__ by Terraform from "file-processor/worker"
resource "aws_ecr_repository" "worker" {
  force_delete         = null
  image_tag_mutability = "MUTABLE"
  name                 = "file-processor/worker"
  region               = "us-east-2"
  tags                 = {}
  tags_all             = {}
  encryption_configuration {
    encryption_type = "AES256"
  }
  image_scanning_configuration {
    scan_on_push = false
  }
}
