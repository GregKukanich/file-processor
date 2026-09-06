# __generated__ by Terraform
# Please review these resources and move them into your main configuration files.

# __generated__ by Terraform from "file-processor-dev-942440204471-us-east-2-an"
resource "aws_s3_bucket" "uploads" {
  bucket              = "file-processor-dev-942440204471-us-east-2-an"
  force_destroy       = null
  object_lock_enabled = false
  tags                = {}
  tags_all            = {}
}

# __generated__ by Terraform from "file-processor-dev-942440204471-us-east-2-an"
resource "aws_s3_bucket_public_access_block" "uploads" {
  block_public_acls       = true
  block_public_policy     = true
  bucket                  = "file-processor-dev-942440204471-us-east-2-an"
  ignore_public_acls      = true
  restrict_public_buckets = true
}

# __generated__ by Terraform from "file-processor-dev-942440204471-us-east-2-an"
resource "aws_s3_bucket_server_side_encryption_configuration" "uploads" {
  bucket                = "file-processor-dev-942440204471-us-east-2-an"
  expected_bucket_owner = null
  rule {
    bucket_key_enabled = true
    apply_server_side_encryption_by_default {
      kms_master_key_id = null
      sse_algorithm     = "AES256"
    }
  }
}

# __generated__ by Terraform from "file-processor-dev-942440204471-us-east-2-an"
resource "aws_s3_bucket_versioning" "uploads" {
  bucket                = "file-processor-dev-942440204471-us-east-2-an"
  expected_bucket_owner = null
  mfa                   = null
  versioning_configuration {
    status = "Disabled"
  }
}
