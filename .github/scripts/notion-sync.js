const { Client } = require('@notionhq/client');

const notion = new Client({
  auth: process.env.NOTION_TOKEN,
});

async function syncCommitToNotion() {
  const commitSha = process.env.GITHUB_SHA;
  const commitMessage = process.env.COMMIT_MESSAGE;
  const repoName = process.env.GITHUB_REPOSITORY;
  const commitUrl = `https://github.com/${repoName}/commit/${commitSha}`;
  
  try {
    await notion.pages.create({
      parent: {
        database_id: process.env.NOTION_DATABASE_ID,
      },
      properties: {
        'Name': {
          rich_text: [
            {
              text: {
                content: commitMessage || 'No commit message'
              }
            }
          ]
        },
        'Commit': {
          rich_text: [
            {
              text: {
                content: commitMessage || 'No commit message'
              }
            }
          ]
        },
        'Id': {
          rich_text: [
            {
              text: {
                content: commitSha.substring(0, 7)
              }
            }
          ]
        },
        'Url': {
          url: commitUrl
        },
        'description': {
          rich_text: [
            {
              text: {
                content: `Commit from ${repoName}`
              }
            }
          ]
        }
      }
    });
    
    console.log('✅ Commit synced to Notion successfully');
  } catch (error) {
    console.error('❌ Error syncing to Notion:', error.message);
    process.exit(1);
  }
}

syncCommitToNotion();