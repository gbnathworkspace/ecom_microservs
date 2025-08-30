# Issue Tracking Guide

## 🚀 Quick Start

### Creating Issues
1. Go to [GitHub Issues](https://github.com/your-username/ecom_microservs/issues)
2. Click "New Issue"
3. Select the appropriate template:
   - 🐛 **Bug Report** - For bugs and errors
   - ✨ **Feature Request** - For new features or enhancements  
   - 🚀 **CI/CD Issue** - For deployment and pipeline problems

## 📋 Current Issues to Track

### 🔥 High Priority
1. **CI/CD Health Check Failure**
   - Status: Open
   - Description: Deployment failing with "Could not connect to server" on port 8080
   - Labels: `ci/cd`, `bug`, `high-priority`

2. **Swagger Authorization Button Missing**
   - Status: Resolved ✅
   - Description: JWT authentication not configured in Swagger UI
   - Labels: `bug`, `documentation`

### 🐛 Bugs
1. **Spring Boot 3.5.3 Compatibility Issues**
   - Status: Resolved ✅  
   - Description: springdoc-openapi version incompatibility
   - Labels: `bug`, `dependencies`

### 🚧 Enhancements
1. **Database Profile Management**
   - Status: Resolved ✅
   - Description: Separate dev (H2) and production (PostgreSQL) profiles
   - Labels: `enhancement`, `configuration`

## 🏷️ Labels

### Priority
- `high-priority` - Urgent issues blocking development/deployment
- `medium-priority` - Important but not blocking
- `low-priority` - Nice to have improvements

### Type  
- `bug` - Something isn't working
- `enhancement` - New feature or improvement
- `ci/cd` - Deployment/pipeline related
- `documentation` - Documentation improvements
- `dependencies` - Dependency related issues

### Status
- `needs-investigation` - Requires analysis
- `in-progress` - Currently being worked on
- `blocked` - Waiting on external factors
- `ready-for-review` - Ready for code review

## 📊 Project Board

Issues are automatically organized into columns:
- **To Do** - New issues
- **Bug Triage** - Bug reports needing investigation  
- **Backlog** - Feature requests and enhancements
- **DevOps** - CI/CD and deployment issues
- **In Progress** - Currently being worked on
- **Review** - Ready for review
- **Done** - Completed issues

## 🔄 Workflow

1. **Create Issue** using templates
2. **Add Labels** for categorization
3. **Assign** to team members
4. **Move to appropriate column** in project board
5. **Update status** as work progresses
6. **Close** when resolved with summary

## 📝 Best Practices

- Use clear, descriptive titles
- Include reproduction steps for bugs
- Add screenshots/logs when helpful
- Reference related issues with `#issue-number`
- Update issues when status changes
- Close with summary of solution